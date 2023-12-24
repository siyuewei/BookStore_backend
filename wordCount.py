import os
import shutil
import string
from pyspark import SparkConf, SparkContext

# 设置Spark应用名和配置
conf = SparkConf().setAppName("WordCount")
sc = SparkContext(conf=conf)

def delete_directory(directory_path):
    try:
        shutil.rmtree(directory_path)
        print(f"Directory {directory_path} deleted successfully.")
    except OSError as e:
        print(f"Error deleting directory {directory_path}: {e}")

# 读取输入目录中的所有文件，并将每个txt文件的内容作为一个RDD
input_directory = "E:\\code\\eBook\\back\\input"
text_files = sc.wholeTextFiles(input_directory).values()

# 定义关键词
keywords = ["code", "fiction", "history", "philosophy"]

# 进行映射和过滤操作，累加每个关键词的计数
counts = (
    text_files.flatMap(lambda content: content.lower().split())  # 将每个文件的内容按空格拆分为单词
    .map(lambda word: word.strip(string.punctuation).strip())  # 去除标点并去除前后空格
    .filter(lambda word: word in keywords) # 过滤出包含关键词的单词
    .map(lambda word: (word, 1))  # 为每个单词赋值1
    .reduceByKey(lambda a, b: a + b)  # 累加相同单词的计数
)

# 保存结果到输出文件
output_directory = "E:\\code\\eBook\\spark\\output"
if os.path.exists(output_directory):
    delete_directory(output_directory)

counts.saveAsTextFile(output_directory)

# 停止 SparkContext
sc.stop()

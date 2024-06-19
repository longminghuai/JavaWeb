import os
import requests

# 创建一个目录来保存图片
def create_directory(directory):
    if not os.path.exists(directory):
        os.makedirs(directory)

# 从指定 URL 下载图片并保存到本地
def download_image(url, save_path):
    try:
        response = requests.get(url)
        response.raise_for_status()  # 检查请求是否成功
        with open(save_path, 'wb') as file:
            file.write(response.content)
        print(f"成功下载图片: {save_path}")
    except Exception as e:
        print(f"下载图片失败: {url}, 错误: {e}")

def main():
    # 基础 URL 模板
    base_url = "https://static-data.gaokao.cn/upload/logo/{}.jpg"
    # 目标文件夹
    save_directory = "images"
    # 创建文件夹
    create_directory(save_directory)

    # 下载并保存所有图片
    for i in range(3000, 3800):
        url = base_url.format(i)
        file_name = f"{i}.jpg"
        save_path = os.path.join(save_directory, file_name)
        download_image(url, save_path)

if __name__ == "__main__":
    main()

# #求各院校各专业在湖南的招生人数
# import requests
# import csv
# import time
# import logging

# logging.basicConfig(filename='scraper.log', level=logging.INFO)

# headers = {
#     'Accept': 'application/json',
#     'Content-Type': 'application/json;charset=utf-8',
#     'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44'
# }

# def get_detail(year, sid, province_code):
#     lst = []
#     retries = 3
#     for attempt in range(retries):
#         try:
            
#             # https://static-data.gaokao.cn/www/2.0/schoolspecialplan/42/2023/43.json
#             url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialplan/{sid}/{year}/{province_code}.json"
#             print(url)
#             response = requests.request("GET", url, headers=headers, timeout=5)
#             response.raise_for_status()
#             data = response.json().get('data', {})
#             if not data:
#                 logging.warning(f"No data found for {url}")
#                 return lst
#             for key, value in data.items():
#                 if 'item' in value:
#                     lst += value['item']
#             break
#         except requests.RequestException as e:
#             logging.error(f"Request failed (attempt {attempt + 1}/{retries}): {e}")
#             time.sleep(2)  # 等待2秒后重试
#             continue
#     else:
#         logging.error(f"Failed to get data for {sid} after {retries} attempts.")
#     return lst

# def get_school_list(province_id="", is_985="", is_211="", is_dual_class=""):
#     page_size = 30
#     lst_sch = []
#     for i in range(1, 2000):
#         url = f"https://api.eol.cn/web/api/?&keyword=&page={i}&uri=apidata/api/gk/school/lists&size={page_size}&province_id={province_id}"
#         print(url)
#         try:
#             response = requests.request("GET", url, headers=headers, timeout=5)
#             response.raise_for_status()
#             data = response.json()
#             num = int(data['data']['numFound'])
#             for item in data['data']['item']:
#                 lst_sch.append([item['school_id'], str(item['code_enroll']), item['name'],
#                                item['province_name'], item['city_name']])
#             if i * page_size >= num:
#                 break
#         except requests.RequestException as e:
#             logging.error(f"Request failed for school list page {i}: {e}")
#             break
#     return lst_sch

# def main():
#     save_file = open('result2.csv', 'w', newline='', encoding='utf-8')
#     output = csv.writer(save_file)
#     output.writerow(['年份', '省份', '院校代码', '学校', '学科门类', '专业类',
#                     '专业名称', '计划招生人数','类别'])
#     for i in range(11,66):
#         province_code = str(i)  # 修改为你需要的省份代码
#         lst_sch = get_school_list(province_id=province_code)
#         for sid, scode, sname, province, city in lst_sch:
#             print(sid, scode[:-2], sname, province, city)
#             for year in range(2023, 2022, -1):
#                 data = get_detail(year=year, sid=sid, province_code=province_code)
#                 for item in data:
#                     tmp = [year, province, scode[:-2], sname, item.get('level2_name', ''), item.get('level3_name', ''), item.get('spname', ''),
#                         item.get('num', ''),item.get('type', '')]
#                     output.writerow(tmp)
#                     print(tmp)
#         save_file.close()

# if __name__ == '__main__':
#     main()


import requests
import csv
import time
import logging

logging.basicConfig(filename='scraper.log', level=logging.INFO)

headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json;charset=utf-8',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44'
}

def get_detail(year, sid, province_code):
    lst = []
    retries = 3
    for attempt in range(retries):
        try:
            url = f"https://static-data.gaokao.cn/www/2.0/schoolspecialplan/{sid}/{year}/{province_code}.json"
            print(url)
            response = requests.get(url, headers=headers, timeout=5)
            response.raise_for_status()
            data = response.json().get('data', {})
            if not data:
                logging.warning(f"No data found for {url}")
                return lst
            for key, value in data.items():
                if 'item' in value:
                    lst += value['item']
            break
        except requests.RequestException as e:
            logging.error(f"Request failed (attempt {attempt + 1}/{retries}): {e}")
            time.sleep(2)  # 等待2秒后重试
            continue
    else:
        logging.error(f"Failed to get data for {sid} after {retries} attempts.")
    return lst

def get_school_list(province_id="", is_985="", is_211="", is_dual_class=""):
    page_size = 30
    lst_sch = []
    for i in range(1, 2000):
        url = f"https://api.eol.cn/web/api/?&keyword=&page={i}&uri=apidata/api/gk/school/lists&size={page_size}&province_id={province_id}"
        print(url)
        try:
            response = requests.get(url, headers=headers, timeout=5)
            response.raise_for_status()
            data = response.json()
            num = int(data['data']['numFound'])
            for item in data['data']['item']:
                lst_sch.append([item['school_id'], str(item['code_enroll']), item['name'],
                               item['province_name'], item['city_name']])
            if i * page_size >= num:
                break
        except requests.RequestException as e:
            logging.error(f"Request failed for school list page {i}: {e}")
            break
    return lst_sch

def main():
    with open('zsnum.csv', 'w', newline='', encoding='utf-8') as save_file:
        output = csv.writer(save_file)
        output.writerow(['年份', '省份', '院校代码', '学校', '学科门类', '专业类',
                        '专业名称', '计划招生人数', '类别'])
        
        for i in range(11,100):
            province_code = str(i)  # 修改为你需要的省份代码
            lst_sch = get_school_list(province_id=province_code)
            
            for sid, scode, sname, province, city in lst_sch:
                print(sid, scode[:-2], sname, province, city)
                for year in range(2023, 2022, -1):
                    data = get_detail(year=year, sid=sid, province_code=province_code)
                    for item in data:
                        tmp = [year, province, scode[:-2], sname, item.get('level2_name', ''), item.get('level3_name', ''), item.get('spname', ''),
                            item.get('num', ''), item.get('type', '')]
                        output.writerow(tmp)
                        print(tmp)

if __name__ == '__main__':
    main()

import csv
from datetime import date, datetime, time, timedelta

num = 0
first = None
number = 0
myset = set()
initTime=datetime.strptime("2018-10-08 00:00:00.000", '%Y-%m-%d %H:%M:%S.%f')
with open('dataset.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 0
    lines = []
    for row in csv_reader:
        if line_count < 10:
            lines.append(row)
        else:
            break
        line_count+=1
# lines = list(csv.DictReader(open('dataset.csv', 'r')))
# print(lines[1])

    # for row in csv_reader:
    #     if line_count == 0:
    #         print(f'Column names are {", ".join(row)}')
    #         line_count += 1
    #     try:
    #         ts = datetime.strptime(row["ts"], '%Y-%m-%d %H:%M:%S.%f')
    #         delta = ts - initTime
    #     except:
    #         ts = datetime.strptime(row["ts"], '%Y-%m-%d %H:%M:%S')
    #         delta = ts - initTime
    #     if delta > timedelta(hours=1):
    #         break
    #     myset.add(row["node_id"])
    #     print(f'\tnode_id= {row["node_id"]} lon= {row["lon"]}  lat= {row["lat"]}  speed= {row["speed"]} ')
    #     line_count += 1
    # print(f'Processed {line_count} lines.')
    # print(myset)
    # print(len(myset))
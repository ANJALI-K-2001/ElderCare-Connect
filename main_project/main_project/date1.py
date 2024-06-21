# from datetime import datetime, timedelta

# def generate_dates(start_date_str, end_date_str):
#     start_year, start_month, start_day = map(int, start_date_str.split('/'))
#     end_year, end_month, end_day = map(int, end_date_str.split('/'))

#     start_date = datetime(start_year, start_month, start_day)
#     end_date = datetime(end_year, end_month, end_day)

#     current_date = start_date
#     date_list = []

#     while current_date <= end_date:
#         date_list.append(current_date.strftime('%Y/%m/%d'))
#         current_date += timedelta(days=1)

#     return date_list

# # Example usage:
# start_date_input = input("Enter the start date (YYYY-MM-DD): ")
# end_date_input = input("Enter the end date (YYYY-MM-DD): ")

# try:
#     dates = generate_dates(start_date_input, end_date_input)
#     print("Dates in the given range:")
#     print(dates)
# except ValueError:
#     print("Invalid date format. Please use the format YYYY-MM-DD.")


from datetime import datetime, timedelta

def generate_dates(start_date_str, end_date_str):
    start_day, start_month, start_year = map(int, start_date_str.split('/'))
    end_day, end_month, end_year = map(int, end_date_str.split('/'))

    start_date = datetime(start_year, start_month, start_day)
    end_date = datetime(end_year, end_month, end_day)

    current_date = start_date
    date_list = []

    while current_date <= end_date:
        date_list.append(current_date.strftime('%d/%m/%Y'))
        current_date += timedelta(days=1)

    return date_list

# # Example usage:
# start_date_input = input("Enter the start date (DD/MM/YYYY): ")
# end_date_input = input("Enter the end date (DD/MM/YYYY): ")

# try:
#     dates = generate_dates(start_date_input, end_date_input)
#     print("Dates in the given range:")
#     print(dates)
# except ValueError:
#     print("Invalid date format. Please use the format DD/MM/YYYY.")

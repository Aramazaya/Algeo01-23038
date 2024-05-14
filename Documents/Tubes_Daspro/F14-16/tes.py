from os import path, mkdir
import time
from argparse import *

# F14-Load

parser = ArgumentParser()
parser.add_argument("nama_folder", nargs='?', type=str, default='')
arg = parser.parse_args()
folder = arg.nama_folder



#Fungsi untuk splitting list of string
def splitter(sentence: str, separator:str) -> list:
  splitValue= []
  temp = ""
  j = 0
  for i in sentence:
    j += 1
    if i != separator and j != len(sentence): #Masukkan kata ke temp
      temp += i
    elif i != separator and j == len(sentence): #Jika kata terakhir
      temp += i
      splitValue.append(temp)
      temp = ""
    else: #Kata di-split bila bertemu separator
      splitValue.append(temp)
      temp = ""
  return(splitValue)

def parser(file: str) -> list:
  list = []
  for i in open(file): #baca CSV
    element = splitter(i[:-1], ";") #:-1 untuk cut \n
    list.append(element)
  open(file).close() #ditutup bila sudah selesai
  return(list)

# F15-Save
def save(data_item_inventory, data_monster, data_monster_inventory, data_monster_shop, data_user ):
    """
    :param data_item_inventory:
    :param data_monster:
    :param data_monster_inventory:
    :param data_monster_shop:
    :param data_user:
    """

    folder_name = input(f"Masukkan nama folder: ")
    print()
    print("Saving...\n")
    time.sleep(1)

    #Membuat folder save jika tidak ada folder save
    if not path.exists("data"):
        mkdir("data")
        print("Membuat folder data...")

    folder_path = "data/" + folder_name

    if not path.exists(folder_path):
        mkdir(folder_path)
        print(f"membuat folder{folder_path}...")
        time.sleep(1)

    print()
    # Misalnya matrixnya ini
    # data_item_inventory = [user_id, type, quantity]
    # data_monster = [id, type, atk_power, def_power, hp]
    # data_monster_inventory = [user_id, monster_id, level]
    # data_monster_shop = [monster_id, stock, price]
    # data_user = [id, username, password, role, oc]

    #anggap udh ada max data
    matriks_to_csv(folder_path, "Copy of item_inventory.csv", 3, data_item_inventory)
    matriks_to_csv(folder_path, "Copy of monster.csv", 5, data_monster)
    matriks_to_csv(folder_path, "Copy of monster_inventory.csv", 3, data_monster_inventory)
    matriks_to_csv(folder_path, "Copy of monster_shop.csv", 3, data_monster_shop)
    matriks_to_csv(folder_path, "Copy of user.csv", 5, data_user)

    print(f"Berhasil menyimpan data di folder {folder_path}!")
    time.sleep(1)


# F16-Exit
def exit_game(data_item_inventory, data_monster, data_monster_inventory, data_monster_shop, data_user ):
    """
    :param data_item_inventory:
    :param data_monster:
    :param data_monster_inventory:
    :param data_monster_shop:
    :param data_user:
    """
    confirm = ""

    # konfirmasi save
    while confirm.lower() != "y" and confirm.lower() != "n":
        confirm = input(f"Apakah Anda mau melakukan penyimpanan file yang sudah diubah? (y/n)")

    if confirm.lower() == 'y':
        save(data_item_inventory, data_monster, data_monster_inventory, data_monster_shop, data_user )

    return True




#custom function
def matriks_to_csv(folder_path, file_name, jumlah_elemen, data_matriks):
    """
    mengubah suatu matriks menjadi file csv
    :param folder_path:
    :param file_name:
    :param jumlah_elemen:
    :param data_matriks:
    """

    file_path = folder_path + '/' + file_name

    # open file
    with open(file_path, 'w') as f:
        if file_name == "Copy of item_inventory":
            f.write("user_id;type;quantity\n")
        elif file_name == "Copy of monster.csv":
            f.write("id;type;atk_power;def_power;hp\n")
        elif file_name == "Copy of monster_inventory.csv":
            f.write("user_id;monster_id;level\n")
        if file_name == "Copy of monster_shop":
            f.write("monster_id;stock;price\n")
        if file_name == "Copy of user.csv":
            f.write("id;username;password;role;oc\n")
            # data_item_inventory = [user_id, type, quantity]
            # data_monster = [id, type, atk_power, def,power, hp]
            # data_monster_inventory = [user_id, monster_id, level]
            # data_monster_shop = [monster_id, stock, price]
            # data_user = [id, username, password, role, oc]

        for i in range(custom_len(data_matriks)):
            string_list = custom_reverse_split(data_matriks[i], jumlah_elemen, ";")
            f.write(string_list)


def custom_split(string_list, jumlah_elemen, pemisah):
    """
    memisahkan suatu string menjadi elemen-elemennya berdasarkan suatu pemisah
    :param string_list:
    :param jumlah_elemen:
    :param pemisah:
    :return: array
    """
    data_list = [None for i in range(jumlah_elemen)]
    index = 0
    elemen_sementara = ""

    while True:
        # jika char adalah pemisah atau akhir baris, append elemen baru pada data_list
        if string_list[index] == pemisah or string_list[index] == "\n":
            data_list = custom_append(data_list, elemen_sementara, jumlah_elemen)
            elemen_sementara = ""

            if string_list[index] == "\n":
                return data_list

        else:
            # menambah char pada elemen_sementara
            elemen_sementara += string_list[index]

        index += 1


def custom_append(array, elemen, max_array):
    """
    menambah elemen pada akhir array (instansi None) pertama pada array tersebut
    jika array penuh, array tidak akan ganti
    :param array:
    :param elemen:
    :param max_array:
    :return: array
    """
    if custom_len(array, max_array) < max_array:
        # elemen dimasukkan pada index efektif terakhir array
        array[custom_len(array, max_array)] = elemen

    return array

def custom_len(array, max_array):
    """
    mencari panjang suatu array dengan jumlah elemen maksimum tertentu
    :param array:
    :param max_array:
    :return: integer
    """
    # mencari instansi None pertama dan return index tersebut
    for i in range(max_array):
        if array[i] is None:
            return i

    return max_array

def custom_reverse_split(data_list, jumlah_elemen, pemisah):
    """
    mengubah array menjadi string dengan suatu pemisah
    :param data_list:
    :param jumlah_elemen:
    :param pemisah:
    :return: string
    """
    string_list = ""

    # untuk setiap elemen ditambahkan kepada string dan ditambahkan pemisahnya sampai elemen terakhir
    for i in range(jumlah_elemen):
        string_list += str(data_list[i])
        if i < jumlah_elemen - 1:
            string_list += pemisah
        else:
            string_list += "\n"

    return string_list



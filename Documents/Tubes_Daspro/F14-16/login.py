import time

def login(nama, role, dataUser, ):
    """
    :param nama:
    :param role:
    :param dataUser:
    :return: nama, role
    """

    berhasil_login = False
    input_user = ""

    # memastikan akses sesuai
    if role is not None:
        print(f"Login gagal!\nAnda telah login dengan username {nama}, silahkan lakukan “logout” sebelum melakukan login kembali.")

    else:
        # input nama dan passwordd
        input_user = input(f"{bcolors.input}Username: {bcolors.endc}")
        input_password = input(f"{bcolors.input}Password: {bcolors.endc}")
        time.sleep(0.5)
        print()

        # cek jika nama terdaftar
        if not CekNamaTerdaftar(input_user, dataUser, max_data_user):
            print(f"Username tidak terdaftar!")

        # cek jika password cocok
        elif not cek_password_cocok(input_password, input_user, dataUser, max_data_user):
            print(f"{bcolors.fail}Password salah!{bcolors.endc}")

        # berhasil login
        else:
            print(f"Selamat datang, {input_user}!")
            print("Masukkan command \"help\" untuk daftar command yang dapat kamu panggil.")
            berhasil_login = True

    # return nama dan role sesuai login
    if berhasil_login:
        role = nama_to_role(input_user, dataUser, max_data_user)
        return input_user, role

    else:
        return nama, role


def CekNamaTerdaftar(nama, dataUser, max_data_user):
    """
    mengecek jika nama tertentu sudah terdaftar atau belum
    :param nama:
    :param dataUser:
    :param max_data_user:
    :return: boolean
    """
    # mencari instansi munculnya nama pada data_user
    for i in range(len(dataUser)):
        if dataUser[i][0] == nama:
            return True

    return False

def cek_password_cocok(password, nama, dataUser):
    """
    mengecek jika password suatu nama benar/cocok atau tidak
    :param password:
    :param nama:
    :param data_user:
    :param max_data_user:
    :return: boolean
    """
    # cek instansi munculnya nama
    for i in range(len(dataUser)):
        if dataUser[i][0] == nama:
            # cek jika password cocok
            if password == dataUser[i][1]:
                return True
            return False


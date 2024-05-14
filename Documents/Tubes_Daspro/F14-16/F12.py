from mockUpMain import load
#F12 : Shop Management

# Fungsi untuk print array dalam bentuk tabel
def printTbl(arr):
  for j in range (len(arr)):
      print("|",end = "")
      for i in range (len(arr[j])):
        maxLength = maxLengthArrKol(arr,i)
        print(fixedLength(arr[j][i],maxLength), end ="|")
      print()

# Fungsi mengembalikan maximum panjang karakter di dalam array arr per kolom
def maxLengthArrKol(arr,kol):
  maxArr = 0
  for i in range(len(arr)):
    if len(arr[i][kol]) > maxArr:
        maxArr = len(arr[i][kol])
  return (maxArr+2)

# Fungsi untuk menambahkan karakter kosong sehingga panjangnya sebesar length
def fixedLength(text,length):
  if len(text) >= length:
    text = text[:length]
  else:
    text = (text + " "*length)[:length]
  return text

#Fungsi untuk menghasilkan uniqe id di arr[0]
def generateID(arr):
  idInt = len(arr)
  idStr = str(idInt)
  while isIdExist(idStr,arr):
     idInt = idInt + 1
     idStr = str(idInt)
  return idStr

# Fungsi untuk join arr1 dan arr2 berdasarkan arr1.id = arr2.id
def joinArr(arr1,arr2):
  arrJ = []
  idxJ = 0
  for i in range(len(arr1)):
    if i == 0:
      arrJ.append(arr1[i])
      for j in range(len(arr2[i])):
        if j != 0:
          arrJ[idxJ].append(arr2[0][j])
    else:
      found = False
      j = 0
      while j < len(arr2) and not found:
        if arr1[i][0] == arr2[j][0]:
          arrJ.append(arr1[i])
          idxJ = idxJ + 1
          for k in range (len(arr2[j])):
            if k != 0:
              arrJ[idxJ].append(arr2[j][k])
          found = True
        j = j + 1
  return arrJ

# Fungsi mengembalikan semua arr1 berdasarkan tidak ada arr1.id di arr2.id
def joinArr2(arr1,arr2):
  arrJoin = []
  for i in range (len(arr1)):
    if i == 0:
      arrJoin.append(arr1[i])
    else:
      found = False
      j = 0
      while j < len(arr2) and not found:
        if arr1[i][0] == arr2[j][0]:
          found = True
        j = j + 1
      if not found:
        arrJoin.append(arr1[i])
  return arrJoin

# Fungsi mengembalikan array dari array item inventory yang tidak ada di item shop
def joinArr3(arr1,arr2): #asumsi arr 1 item inv
  arrJoin = []
  for i in range (len(arr1)):
    if i == 0:
      arrJoin.append(arr1[i])
    else:
      found = False
      j = 0
      while j < len(arr2) and not found:
        if arr1[i][1] == arr2[j][1]:
          found = True
        j = j + 1
      if not found:
        arrJoin.append(arr1[i])
  return arrJoin

# Fungsi mengembalikan true jika value input adalah integer
def isInputInt(strInt):
  try:
    val = int(strInt)
    valid = True
  except:
    valid = False
  return valid

# Fungsi untuk menuLihat
def menuLihat():
  arrLihat = []
  arrMonster = []
  arrMonsterShop = []
  arrPotionShop = []
  arrMonster = load("monster.csv")
  arrMonsterShop = load("monster_shop.csv")
  arrPotionShop = load("item_shop.csv")

  aksi = input("Mau lihat apa? (monster/potion): ")
  if aksi.upper() == "MONSTER":
    arrLihat = joinArr(arrMonster,arrMonsterShop)
    printTbl(arrLihat)
    print()
  elif aksi.upper() == "POTION":
    printTbl(arrPotionShop)
    print()
  else:
    print("Pilihan tidak dikenali")

# Fungsi untuk menuTambah
def menuTambah():
  arrTambah = []
  arrMonster2 = []
  arrMonsterShop2 = []
  arrItem = []
  arrItemShop = []
  arrMonster2 = load("monster.csv")
  arrMonsterShop2 = load("monster_shop.csv")
  arrItem = load("item.csv")
  arrItemShop = load("item_shop.csv")
  aksi = input("Mau nambahin apa? (monster/potion): ")
  if aksi.upper() == "MONSTER":
    arrTambah = joinArr2(arrMonster2,arrMonsterShop2)
    printTbl(arrTambah)
    print()
    try:
      idMonster = input("Masukkan id monster: ")
      stok = input("Masukkan stok awal: ")
      intStok = int(stok)
      harga = input("Masukkan harga: ")
      intHarga = int(harga)
    except:
      print("Input yang anda masukkan tidak valid")
    if (isIdExist(idMonster,arrTambah)):
      arrMonsterShop2.append([idMonster,stok,harga])
      updateFile("monster_shop.csv",arrMonsterShop2)
      arr = searchId(idMonster,arrMonster2)
      print(arr[1]+" telah berhasil ditambahkan ke dalam shop!")
    else:
      print("Id monster tidak ada")
  elif aksi.upper() == "POTION":
    arrTambah = joinArr2(arrItem,arrItemShop)
    printTbl(arrTambah)
    print()
    try:
      idItem = input("Masukkan id item: ")
      stok = input("Masukkan stok awal: ")
      intStok = int(stok)
      harga = input("Masukkan harga: ")
      intharga = int(harga)
    except:
      print("Input yang anda masukkan tidak valid")
    if (isIdExist(idItem,arrTambah)):
      arr = searchId(idItem,arrItem)
      print(arr)
      arrItemShop.append([idItem,arr[1],stok,harga])
      updateFile("item_shop.csv",arrItemShop)
      print(arr[1]+" telah berhasil ditambahkan ke dalam shop!")
    else:
      print("Id potion tidak ada")
  else:
    print("Pilihan tidak dikenali")

# Fungsi mengembalikan True jika idText ada di array[0] arr, yaitu id. Case insensitive
def isIdExist(idText,arr):
  found = False
  i = 0
  while i < len(arr) and not found:
    if arr[i][0].upper() == idText.upper():
      found = True
    i = i + 1
  return found

# Fungsi mengembalikan True jika nameType ada di array[1] arr, yaitu nama/type. Case insensitive
def isNameExist(nameType,arr):
  found = False
  i = 0
  while i < len(arr) and not found:
    if arr[i][1].upper() == nameType.upper():
      found = True
    i = i + 1
  return found

# Fungsi mengembalikan array jika idText ada di array[0] arr, yaitu id. Case insensitive
def searchId(idText,arr):
  arrId = []
  found = False
  i = 0
  while i < len(arr) and not found:
    if arr[i][0].upper() == idText.upper():
      found = True
      arrId = arr[i]
    i = i + 1
  return arrId

# Fungsi mengembalikan nama type jika idText ada di array[0] arr, yaitu id. Case insensitive
def searchName(idText,arr):
  found = False
  i = 0
  while i < len(arr) and not found:
    if arr[i][0].upper() == idText.upper():
      found = True
      name = arr[i][1]
    i = i + 1
  return name

# Fungsi mengembalikan array jika idText ada di array[0] arr, yaitu id. Case insensitive
# jika tidak ada mengembalikan nilai 0
def searchId2(idText,arr):
  pos = 0
  found = False
  i = 0
  while i < len(arr) and not found:
    if arr[i][0].upper() == idText.upper():
      found = True
      pos = i
    i = i + 1
  return pos

# Fungsi untuk mengupdate content fileName menjadi array arr dengan format csv
def updateFile(fileName,arr):
  try:
     f = open(fileName,"w")
     for i in range(len(arr)):
       csvStrLine =""
       for j in range(len(arr[i])):
         if j == len(arr[i]) -1:
           csvStrLine = csvStrLine + arr[i][j]+"\n"
         else:
           csvStrLine = csvStrLine + arr[i][j] + ";"
       f.write(csvStrLine)
     f.close()
  except:
     print("Update data ke file gagal!")

# Fungsi untuk menuUbah - belum selesai
def menuUbah():
  arrUbah = []
  arrMonster2 = []
  arrMonsterShop2 = []
  arrItemShop = []
  arrMonster2 = load("monster.csv")
  arrMonsterShop2 = load("monster_shop.csv")
  arrItemShop = load("Item_shop.csv")
  arr =[]

  aksi = input("Mau ubah apa? (monster/potion): ")
  if aksi.upper() == "MONSTER":
    arrUbah = joinArr(arrMonster2,arrMonsterShop2)
    printTbl(arrUbah)
    print()
    idMonster = input("Masukkan id monster: ")
    if (isIdExist(idMonster,arrUbah)):
      name = searchName(idMonster,arrMonster2)
      arr = searchId(idMonster,arrMonsterShop2)
      pos = searchId2(idMonster,arrMonsterShop2)
      try:
        stok = input("Masukkan stok baru: ")
        harga = input("Masukkan harga baru: ")
        if stok == "":
          if harga == "":
            print("Kedua input kosong")
          else:
            intHarga = int(harga)
            #update harga
            arr[2] = harga
            arrUbah[pos] = arr
            updateFile("monster_shop.csv",arrMonsterShop2)
            print(name+" telah berhasil diubah dengan harga baru "+harga+"!")
        else:
          if harga == "":
            intStok = int(stok)
            #update stok
            arr[1] = stok
            arrUbah[pos] = arr
            updateFile("monster_shop.csv",arrMonsterShop2)
            print(name+" telah berhasil diubah dengan stok baru sejumlah "+stok+"!")
          else:
            #update stok dan harga
            arr[1] = stok
            arr[2] = harga
            arrUbah[pos] = arr
            updateFile("monster_shop.csv",arrMonsterShop2)
            print(name+" telah berhasil diubah dengan stok baru sejumlah "+stok+" dan dengan harga baru "+harga+"!")
      except:
        print("Data tidak valid")
    else:
      print("Id monster tidak ada di shop")
  elif aksi.upper() == "POTION":
    printTbl(arrItemShop)
    print()
    idItem = input("Masukkan id potion: ")
    if (isIdExist(idItem,arrItemShop)):
      name = searchName(idItem,arrItemShop)
      arr = searchId(idItem,arrItemShop)
      pos = searchId2(idItem,arrItemShop)
      try:
        stok = input("Masukkan stok baru: ")
        harga = input("Masukkan harga baru: ")
        if stok == "":
          if harga == "":
            print("Kedua input kosong")
          else:
            intHarga = int(harga)
            #update harga
            arr[3] = harga
            arrItemShop[pos] = arr
            updateFile("item_shop.csv",arrItemShop)
            print(name+" telah berhasil diubah dengan harga baru "+harga+"!")
        else:
          if harga == "":
            intStok = int(stok)
            #update stok
            arr[2] = stok
            arrItemShop[pos] = arr
            updateFile("item_shop.csv",arrItemShop)
            print(name+" telah berhasil diubah dengan stok baru sejumlah "+stok+"!")
          else:
            #update stok dan harga
            arr[2] = stok
            arr[3] = harga
            arrItemShop[pos] = arr
            updateFile("item_shop.csv",arrItemShop)
            print(name+" telah berhasil diubah dengan stok baru sejumlah "+stok+" dan dengan harga baru "+harga+"!")
      except:
        print("Data tidak valid")
    else:
      print("Id potion tidak ada di shop")

  else:
    print("Pilihan tidak dikenali")

# Fungsi untuk menuHapus
def menuHapus():
  arrHapus = []
  arrMonster2 = []
  arrMonsterShop2 = []
  arrItemShop = []
  arrMonster2 = load("monster.csv")
  arrMonsterShop2 = load("monster_shop.csv")
  arrItemShop = load("item_shop.csv")

  aksi = input("Mau hapus apa? (monster/potion): ")
  if aksi.upper() == "MONSTER":
    arrHapus = joinArr(arrMonster2,arrMonsterShop2)
    printTbl(arrHapus)
    print()
    idMonster = input("Masukkan id monster: ")
    if (isIdExist(idMonster,arrHapus)):
      flagYN = input("Apakah anda yakin ingin menghapus " + idMonster + " dari shop (y/n)? ")
      if flagYN.upper() == "Y":
         arr = searchId(idMonster,arrMonsterShop2)
         arrMonsterShop2.remove(arr)
         updateFile("monster_shop.csv",arrMonsterShop2)
         print(idMonster+" telah berhasil dihapus dari shop!")
    else:
      print("Id monster tidak ada di shop")
  elif aksi.upper() == "POTION":
    arrHapus = arrItemShop
    printTbl(arrHapus)
    print()
    idItem = input("Masukkan id item: ")
    if (isIdExist(idItem,arrHapus)):
      name = searchName(idItem,arrHapus)
      flagYN = input("Apakah anda yakin ingin menghapus " + name + " dari shop (y/n)? ")
      if flagYN.upper() == "Y":
         arr = searchId(idItem,arrItemShop)
         arrItemShop.remove(arr)
         updateFile("item_shop.csv",arrItemShop)
         print(name +" telah berhasil dihapus dari shop!")
  else:
    print("Pilihan tidak dikenali")

#F12 : SHOP MANAGEMENT

def runShop(user):
  print("Halooo "+ user + ", Selamat Datang Kembali!")

  loop = True
  while loop:
    print()
    aksi = input("Pilih Aksi (lihat/tambah/ubah/hapus/keluar): ")
    if aksi.upper() == "LIHAT":
    #print("Dari file shop.py, filenya adalah "+args.monsterFileName)
      menuLihat()
    elif aksi.upper() == "TAMBAH":
      menuTambah()
    elif aksi.upper() == "UBAH":
      menuUbah()
    elif aksi.upper() == "HAPUS":
      menuHapus()
    elif aksi.upper() == "KELUAR":
      print("Bye " + user + ", see you again!")
      loop = False
    else:
      print("Pilihan tidak dikenali")
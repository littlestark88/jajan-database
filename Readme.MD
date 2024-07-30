# API Spec

## Register User
Request :
- Method : POST
- Endpoint : `/register`
- Body Request :
```json
{
  "email" : "",
  "password" : "",
  "phone_number" :  12312
}
```

Response Success : 
```json
{
  "message" : "Berhasil Daftar akun !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Invalid Email and Phone Number :
```json
{
  "message" : "Email atau no HP Sudah Terdaftar, silahkan login atau gunakan feature lupa password !",
  "token" : ""
}
```

=============================================================================================
## Login User
Request :
- Method : POST
- Endpoint : `/login`
- Body Request :

```json
{
  "email": "",
  "password": "",
  "data": ""
}
```

Response Success :
```json
{
  "message" : "Berhasil Login !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Invalid :
```json
{
  "message" : "Mohon Periksa kembali email anda",
  "token" : ""
}
```

Response Invalid Password :
```json
{
  "message" : "Password Yang anda gunakan salah!, Mohon Periksa Kembali",
  "token" : ""
}
```
=============================================================================================
## Verification User
Request :
- Method : PUT
- Endpoint : `/{userId}/verification?true`

Response Success :
```json
{
  "message" : "Berhasil Verification !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Invalid :
```json
{
  "message" : "Akun Anda sudah di verifikasi",
  "token" : ""
}
```

=============================================================================================
## Change Password User
Request :
- Method : PUT
- Endpoint : `/{userId}/change-password`

- Body Request :
```json
{
  "old_password" : "",
  "new_password" : ""
}
```

Response Success :
```json
{
  "message" : "Berhasil ganti password !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Invalid :
```json
{
  "message" : "Password Lama Anda Salah",
  "token" : ""
}
```
=============================================================================================
## Change Phone Number
Request :
- Method : PUT
- Endpoint : `/{userId}/change-phone-number`

- Body Request :
```json
{
  "old_phone_number" : "",
  "new_phone_number" : ""
}
```

Response Success :
```json
{
  "message" : "Berhasil ganti no hp !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Invalid :
```json
{
  "message" : "No HP Sebelum nya berbeda yang telah terdaftar",
  "token" : ""
}
```

=============================================================================================
## Create Store
Request :
- Method : POST
- Endpoint : `/create-store`
- Body Request :
```json
{
  "name_store" : "",
  "image_store" : "",
  "address" : "jl.tes",
  "category_store" : "bakso"
}
```

Response Success :
```json
{
  "message" : "Berhasil Membuat Store !",
  "token" : "fwf123eqdafAFAS231"
}
```

=============================================================================================
## Update Store
Request :
- Method : POST
- Endpoint : `/create-store`
- Body Request :
```json
{
  "name_store" : "",
  "image_store" : "",
  "address" : "jl.tes",
  "category_store" : "bakso"
}
```

Response Success :
```json
{
  "message" : "Berhasil Membuat Store !",
  "token" : "fwf123eqdafAFAS231"
}
```
=============================================================================================
## Verification Store
Request :
- Method : POST
- Endpoint : `{userId}/verification-store?true`

Response Success :
```json
{
  "message" : "Berhasil Membuat Store !",
  "token" : "fwf123eqdafAFAS231"
}
```
=============================================================================================
## List Verification Store
Request :
- Method : GET
- Endpoint : `/list-store-verification`
- Query Param :
  - pageSize: number,
  - page: number

Response Success :
```json
{
  "message" : "Berhasil Mendapatkan List Verification !",
  "token" : "fwf123eqdafAFAS231",
  "data" : [{}]
  
}
```
=============================================================================================
## List Store
Request :
- Method : GET
- Endpoint : `/list-store`
- Query Param :
    - pageSize: number,
    - page: number

Response Success :
```json
{
  "message" : "Berhasil Mendapatkan List Store !",
  "token" : "fwf123eqdafAFAS231",
  "data" : [{}]
  
}
```
=============================================================================================
## Search Store by Name
Request :
- Method : GET
- Endpoint : `/list-store`
- Query Param :
    - pageSize: number,
    - page: number

Response Success :
```json
{
  "message" : "Berhasil Mendapatkan List Store !",
  "token" : "fwf123eqdafAFAS231",
  "data" : [{}]

}
```

=============================================================================================
## Create Product
Request :
- Method : POST
- Endpoint : `{storeId}/create-product-store`
- Body Request :
```json
{
  "name_product_store" : "",
  "image_product_store" : "",
  "price" : "jl.tes",
  "category_product_store" : "bakso"
}
```

Response Success :
```json
{
  "message" : "Berhasil Membuat Product Store !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Success :
```json
{
  "message" : "Berhasil Membuat Product Store !",
  "token" : "fwf123eqdafAFAS231"
}
```

Response Fail Name Same:
```json
{
  "message" : "Nama Product yang anda buat telah ada, mohon lakukan update jika ingin ada perubahan!",
  "token" : "fwf123eqdafAFAS231"
}
```
=============================================================================================
## Update Product
Request :
- Method : PUT
- Endpoint : `{storeId}/update-product-store`
- Body Request :
```json
{
  "name_product_store" : "",
  "image_product_store" : "",
  "price" : "jl.tes",
  "category_product_store" : "bakso"
}
```

Response Success :
```json
{
  "message" : "Berhasil Membuat Product Store !",
  "token" : "fwf123eqdafAFAS231"
}
```
=============================================================================================
## Delete Product
Request :
- Method : DELETE
- Endpoint : `{storeId}/delete-product-store?nameProduct`
- Body Request :

Response Success :
```json
{
  "message" : "Berhasil Menghapus product!",
  "token" : "fwf123eqdafAFAS231"
}
```
=============================================================================================
## List Product
Request :
- Method : GET
- Endpoint : `{userId}/list-product-user`
- Query Param :
    - pageSize: number,
    - page: number

Response Success :
```json
{
  "message" : "Berhasil Mendapatkan List Product !",
  "token" : "fwf123eqdafAFAS231",
  "data" : [{}]
  
}
```

=============================================================================================
## List Product
Request :
- Method : GET
- Endpoint : `/list-product`
- Query Param :
    - pageSize: number,
    - page: number

Response Success :
```json
{
  "message" : "Berhasil Mendapatkan List Product !",
  "token" : "fwf123eqdafAFAS231",
  "data" : [{}]
  
}
```

=============================================================================================
## Detail Product



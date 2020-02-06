const db = require('./db')
const utils=require('./utils')
const express = require('express')
const multer = require('multer')
const upload = multer({ dest: 'thumbnails/'})


const router = express.Router()

//showing the list of user(admin)
router.get('/', (request, response) => {
    const connection = db.connect()
    const statement = `select * from user_info`
    connection.query(statement, (error, data) => {
        connection.end()
        const users = []
        for (let index = 0; index < data.length; index++) {
            const user = data[index]
            users.push({
                first_name:user['first_name'],
                last_name:user['last_name'],
                gender:user['gender'],
                email_id: user['email_id'],
                password: user['password'],
                mobile_no:user['mobile_no'],
                location_id:user['location_id'],
                pincode:user['pincode']
            })
        }
        response.send(utils.createResult(error, users))
    })
})

//adding user or registering user from client(user)(angular) side.
router.post('/',upload.single('thumbnail'), (request, response) => {
    const {first_name,last_name,gender,email_id,password,mobile_no,pincode} = request.body
    //const encryptedPassword = '' + cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `insert into user_info (first_name,last_name,gender,email_id,password,mobile_no,pincode) values ('${first_name}','${last_name}','${gender}','${email_id}','${password}',${mobile_no},'${pincode}')`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

//adding user or registering user from client(user)(android) side without pincode.
router.post('/register',upload.single('thumbnail'), (request, response) => {
    const {first_name,last_name,gender,email_id,password,mobile_no} = request.body
    //const encryptedPassword = '' + cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `insert into user_info (first_name,last_name,gender,email_id,password,mobile_no) values ('${first_name}','${last_name}','${gender}','${email_id}','${password}',${mobile_no})`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

//adding product into the cart of specific user(whoever logged in) at angular side
router.post('/cart',upload.single('thumbnail'), (request, response) => {
    const {p_id,email_id} = request.body
    //const encryptedPassword = '' + cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `insert into add_to_cart (p_id,email_id) values (${p_id},'${email_id}')`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})
// router.post('/cart/:user_id',upload.single('thumbnail'), (request, response) => {
//     const user_id = request.params.user_id
//     const {p_id, quantity} = request.body 
//     //const encryptedPassword = '' + cryptoJs.MD5(password)
//     const connection = db.connect()
//     const statement = `insert into cart (user_id,p_id,quantity) values (${user_id},${p_id},${quantity})`
//     connection.query(statement, (error, data) => {
//         connection.end()
//         response.send(utils.createResult(error, data))
//     })
// })


//adding the product into cart of specific user(whoever logged in)(android side)
router.get('/cartitem/:user_id', (request, response) => {
    const user_id = request.params.user_id

    const connection = db.connect()
    const statement = `select p.p_id,l.lc_product_name,p.description,(p.weight_per_garm*(g.daily_rate_per_gram+l.lc_charge))*3 as Total,p.image,p.category_id,a.sr_no,a.quantity from cart a inner join product p on a.p_id=p.p_id inner join labour_chart l on p.lc_product_code=l.lc_product_code inner join gold_rate g on l.purity=g.purity where a.user_id=${user_id}`
    //const statement = `select product.id as productId, product.title as title, product.description as description, product.price as price, product.thumbnail as thumbnail, product.categoryId as categoryId, cart.id as cartId, cart.quantity as quantity from cart, product where cart.productId = product.id and userId = ${id}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

//delete the product from cart of specific user(whoever logged in)(android side)
router.delete('/cart/:sr_no', (request, response) => {
    const sr_no = request.params.sr_no

    const connection = db.connect()
    const statement = `delete from cart where sr_no = ${sr_no}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

//update the product qunatity into cart of specific user(whoever logged in)(android side)
router.put('/cart/:sr_no', (request, response) => {
    const sr_no = request.params.sr_no
    const {quantity} = request.body 

    const connection = db.connect()
    const statement = `update cart set quantity = ${quantity} where sr_no = ${sr_no}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

//adding the product into cart of specific user(whoever logged in)(android side) but the product is already present then its quantity will get increment.
router.post('/cart/:user_id', (request, response) => {
    const user_id = request.params.user_id
    const {p_id, quantity} = request.body 
    const connection = db.connect()

    const statement1 = `select * from cart where user_id = ${user_id} and p_id = ${p_id}`
    connection.query(statement1, (error, items) => {
        if (items.length == 0) {
            const statement = `insert into cart ( user_id, p_id,quantity) values(${user_id}, ${p_id}, ${quantity})`
            connection.query(statement, (error, data) => {
                connection.end()
                response.send(utils.createResult(error, data))
            })
        } else {
            connection.end()
            response.send(utils.createResult(error, items))
        }
    })

    
})

//signin/login for both android as well as angular
router.post('/login', (request, response) => {
    const {email_id, password} = request.body
    //const encryptedPassword = '' + cryptoJs.MD5(password)
    const connection = db.connect()
    const statement = `select * from user_info where email_id = '${email_id}' and password = '${password}'`
    connection.query(statement, (error, users) => {
        connection.end()
        
        if (users.length == 0) {
            response.send(utils.createResult('user does not exist'))
        } else {
            const user = users[0]
            const info = {
                email_id: user['email_id'],
                password: user['password'],
                user_id:user['user_id'],
                first_name:user['first_name']
            }
            response.send(utils.createResult(null, info))
        }
    })
})

//showing the cart product of user whoever added into their cart(angular side).
router.get('/:email_id', (request, response) => {
    const {email_id} = request.params
    const connection = db.connect()
    const statement = `select a.*,p.image,l.lc_product_name,(p.weight_per_garm*(g.daily_rate_per_gram+l.lc_charge))*3 as Total from add_to_cart a inner join product p on a.p_id=p.p_id inner join labour_chart l on p.lc_product_code=l.lc_product_code inner join gold_rate g on l.purity=g.purity where a.email_id='${email_id}'`
    
    connection.query(statement, (error, data) => {

        // delete the products from this category
        //const statement2 = `delete from category where category_id = '${category_id}'`
        //connection.query(statement2, (error, data) => {
            connection.end()
            response.send(utils.createResult(error, data))
        })
    })

    router.get('/getuserid:user_id', (request, response) => {
        const {email_id} = request.params
        const connection = db.connect()
        const statement = `select user_id from user_info where user_id='${user_id}'`
        
        connection.query(statement, (error, users) => {
    
            // delete the products from this category
            //const statement2 = `delete from category where category_id = '${category_id}'`
            //connection.query(statement2, (error, data) => {
                connection.end() 
                if (users.length == 0) {
                    response.send(utils.createResult('user does not exist'))
                } else {
                    const user = users[0]
                    const info = {
                        user_id: user['user_id']
                       
                    }
                }

                response.send(utils.createResult(null, info))
            })
        })

//delete the product from cart whatever user user added into their cart(angular side)
    router.delete('/:sr_no', (request, response) => {
        const {sr_no} = request.params
       //const {email_id} = request.body
        const connection = db.connect()
        const statement = `delete from add_to_cart where sr_no = ${sr_no}`
        
        connection.query(statement, (error, data) => {
    
            // delete the products from this category
            //const statement2 = `delete from category where category_id = '${category_id}'`
            //connection.query(statement2, (error, data) => {
                connection.end()
                response.send(utils.createResult(error, data))
            })
        })

        //getting the user details(angular side)
        router.get('/details/:email_id', (request, response) => {
            const {email_id} = request.params
            const connection = db.connect()
            const statement = `select user_info.* from user_info where  email_id = '${email_id}'`
            connection.query(statement, (error, users) => {
                connection.end()
                if (users.length > 0) {
                    response.send(utils.createResult(error, users[0]))
                } else {
                    response.send(utils.createResult('user does not exist'))
                }
            })
        })
//editing the user profile(angular side)
        router.put('/:email_id', (request, response) => {
             const email_id = request.params.email_id
             const {first_name,last_name,gender,mobile_no,pincode} = request.body
             const connection = db.connect()
             const statement = `update user_info set first_name = '${first_name}',last_name='${last_name}', gender='${gender}',mobile_no='${mobile_no}',pincode='${pincode}' where email_id = '${email_id}'`
             connection.query(statement, (error, data) => {
                 connection.end()
                 response.send(utils.createResult(error, data))
             })
         })
module.exports = router

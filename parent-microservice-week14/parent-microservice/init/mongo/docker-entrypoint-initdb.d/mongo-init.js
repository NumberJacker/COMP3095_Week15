print('START');

//get access to the product-service database
db = db.getSiblingDB('product-service');

db.createUser(
    {
        user: 'admin',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'product-service' }],
    },
);

db.createCollection('user');

print('END');
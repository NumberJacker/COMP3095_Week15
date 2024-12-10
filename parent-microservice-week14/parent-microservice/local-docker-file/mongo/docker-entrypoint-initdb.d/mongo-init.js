// You can add this to a separate file named "mongo-init.js"
// Make sure to customize the username, password, and database as needed.
// This script will create the mongoadmin user with readWrite access to the product-service database.
// The root user with root privileges is also created during container initialization.
//mongo-init.js
print(' ************** START **********************');

//get access to the product-service database or create it if it does not exist
//db = db.getSiblingDB('admin');
//db.createUser({
//  user: 'mongoadmin',
//  pwd: 'password',
//  roles: [{ role: 'root', db: 'admin' }],
//});

try {
	print('************ Create product-service database ********************')
	db = db.getSiblingDB('product-service');

	print(' ****** Create mongoadmin user for product-service **********')
	db.createUser({
	  user: 'admin',
	  pwd: 'password',
	  roles: [{ role: 'readWrite', db: 'product-service' }],
	});

    db.createCollection('user');

    print(' *** MongoDB initialization successful. *** ');
} catch(error) {
	 print('********* ERROR during MongoDB initialization: *********** ', error);
}

print('************** END **********************');
db = db.getSiblingDB('techfolio');

db.createUser({
    user: 'techfolio_user',
    pwd: 'secure_password123',
    roles: [
        {
            role: 'readWrite',
            db: 'techfolio',
        },
    ],
});

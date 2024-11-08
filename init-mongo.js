db.createUser({
    user: "nilton",
    pwd: "pwdnilton",
    roles: [
      { role: "readWrite", db: "nomeDoBanco" }
    ]
  });
  
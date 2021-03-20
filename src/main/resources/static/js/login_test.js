function login() {
    //发异步，把数据提交给后端
    fetch("http://localhost:9999/login", {
        method: "post",
        body: JSON.stringify({
            mngId: 10000,
            mngPwd: "123456",
        }),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(res => res.json())
        .then(data => console.log(data));
}



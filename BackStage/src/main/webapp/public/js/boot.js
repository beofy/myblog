requirejs.config({
    //By default load any module IDs from js/lib
    //baseUrl: 'public/js',
    paths: {
        "jquery":'lib/jquery',
        "layui":"layui/layui",
        "encrypt":"encrypt/encrypt",//rsa工具
        "3DES":"encrypt/3DES"
    },
    shim:{

    }
});


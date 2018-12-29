requirejs.config({
    //By default load any module IDs from js/lib
    //baseUrl: 'public/js',
    paths: {
        jquery:'lib/jquery',
        layui:"layui/layui",
        "crypto-js":"encrypt/crypto-js/core",//des加密工具
        encrypt:"encrypt/encrypt"//rsa工具
    },
    shim:{


    }


});


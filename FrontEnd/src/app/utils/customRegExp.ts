export const customRegExp = {
    //no acepta la barra invertida \
    stringPattern: /^[a-zA-Z\sÁáÉéÍíÓóÚúÑñÜü]+$/,
    integerPattern: /^[0-9]+$/,
    stringIntegerPattern: /^[a-zA-Z0-9\sÁáÉéÍíÓóÚúÑñÜü]+$/,
    urlPattern: /^(ht|f)tp(s?)\:\/\/[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z-])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\'\/\\\+&%\$#_]*)?$/,
    stringPhrasePattern: /^[a-zA-Z\sÁáÉéÍíÓóÚúÑñÜü/.,;:()"'\-]+$/,
    stringIntegerPhrasePattern: /^[a-zA-Z0-9\sÁáÉéÍíÓóÚúÑñÜü/.,;:()"'\-¿?!¡]+$/,
    datePattern: /^(\d{4}(\/|-)(0[1-9]|1[0-2])\2([0-2][0-9]|3[0-1]))$/,
    phonePattern: /^[\+|\d]\d+$/,
    locationPattern: /^[a-zA-Z\d\s]*\-[a-zA-Z\d\s]*\-[a-zA-Z\d\s]+$/,
    userNamePassword: /^[a-zA-Z0-9]+$/,
}
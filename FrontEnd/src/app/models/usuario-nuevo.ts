export class UsuarioNuevo {
    usuario: string = "";
    contrasena: string = "";
    constructor(usu: string, contra: string) {
        this.usuario = usu;
        this.contrasena = contra;
    }
}
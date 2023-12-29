package DAO.Classe;

public class ErroDao extends Exception{
    public ErroDao() {
        super("Erro DAO.");
    }

    public ErroDao(String message) {
        super("Erro DAO: "+message);
    }

    public ErroDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroDao(Throwable cause) {
        super(cause);
    }

    public ErroDao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
package org.example.bo;

import org.example.bo.custom.impl.ProgramBOImpl;
import org.example.bo.custom.impl.RegisterBOImpl;
import org.example.bo.custom.impl.StudentBOImpl;
import org.example.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        PROGRAM,USER,STUDENT,PAYMENT,STUDENT_PROGRAM_DETAILS,REGISTERED
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case PROGRAM:
                return new ProgramBOImpl();
            case REGISTERED:
                return new RegisterBOImpl();
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}

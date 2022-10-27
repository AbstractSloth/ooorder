package be.sloth.ooorder.service.exception;

import be.sloth.ooorder.domain.customer.Privilege;

public class NoPrivilegeException extends RuntimeException{

    public NoPrivilegeException(Privilege privilege) {
        super("You do not have " + privilege);
    }
}

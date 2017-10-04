package com.zhuravchak.controller.command.commandlist;

import com.zhuravchak.controller.command.impl.ForwardCommand;
import com.zhuravchak.controller.command.impl.*;
import com.zhuravchak.controller.command.ActionCommand;

public enum CommandEnum {

    /** To change language. */
    UPDATELOCAL (new UpdateLocalCommand()),

    /**To show list of tours*/
    TOUR (new TourCommand()),

    /**The order*/
    ORDER (new OrderCommand()),

    /** The login. */
    LOGIN ( new LoginCommand()),

    /** The register. */
    REGISTER (new RegisterCommand()),

    /** The logout. */
    LOGOUT (new LogoutCommand()),

    /** The buy. */
    BUY (new BuyCommand()),

    /** To calculate price. */
    PRICE(new PriceCommand()),

    /** To make pass hot */
    MAKEHOT (new MakeHotCommand()),

    /** To make pass usual */
    RESETHOT (new ResetHotCommand()),

    /** To set discount for regular users */
    SETDISCOUNT(new SetDiscountCommand()),

    /** The forward. */
    FORWARD (new ForwardCommand());

    /** The command. */
    ActionCommand command;

    /**
     * СommandEnum constructor.
     *
     * @param command the current command
     */
    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets the current command.
     *
     * @return the current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}

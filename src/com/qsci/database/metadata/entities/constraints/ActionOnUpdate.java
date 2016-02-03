package com.qsci.database.metadata.entities.constraints;

public class ActionOnUpdate{
    String actionIfModify;

    public ActionOnUpdate(String actionIfModify) {

        this.actionIfModify = createMessage(actionIfModify);
    }

    public String getActionIfModify() {

        return actionIfModify;
    }

    private String createMessage(String actionIfModify) {
        if (actionIfModify.equals("1")) {
            return "noAction";
        }
        if (actionIfModify.equals("2")) {
            return "KeyCascade";
        }
        if (actionIfModify.equals("3")) {
            return "KeySetNull";
        }
        if (actionIfModify.equals("4")) {
            return "KeySetDefault";
        }
        if (actionIfModify.equals("5")) {
            return "KeyRestrict";
        }
        return "operation not supported";
    }

    @Override
    public String toString() {
        return "ActionOnUpdate " + actionIfModify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionOnUpdate)) return false;

        ActionOnUpdate that = (ActionOnUpdate) o;

        if (actionIfModify != null ? !actionIfModify.equals(that.actionIfModify) : that.actionIfModify != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return actionIfModify != null ? actionIfModify.hashCode() : 0;
    }
}


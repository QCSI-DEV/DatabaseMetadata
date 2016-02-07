package com.qsci.database.metadata.entities.constraints;


public class ActionOnDelete{
    String actionIfModify;

    public ActionOnDelete(String actionIfModify) {

        this.actionIfModify = setAction(actionIfModify);
    }

    public String getActionIfModify() {
        return actionIfModify;
    }

    @Override
    public String toString() {
        return actionIfModify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionOnDelete)) return false;

        ActionOnDelete that = (ActionOnDelete) o;

        if (actionIfModify != null ? !actionIfModify.equals(that.actionIfModify) : that.actionIfModify != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return actionIfModify != null ? actionIfModify.hashCode() : 0;
    }

    private String setAction(String actionIfModify) {
        if (actionIfModify.equals("1")) {
            return "NoAction";
        }
        if (actionIfModify.equals("2")) {
            return "KeyCascade";
        }
        if (actionIfModify.equals("3")) {
            return "KeySetNull";
        }
        if (actionIfModify.equals("4")) {
            return "KeyRestrict";
        }
        if (actionIfModify.equals("5")) {
            return "KeySetDefault";
        }
        return "operation not supported";
    }
}


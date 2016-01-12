package com.qsci.database.metadata.entities.constraints;

public class ForeignKey {
    private String identifier;
    private String fromTable;
    private String toTable;
    private String fromName;
    private String toName;
    private ActionMechanism actionOnUpdate;
    private ActionMechanism actionOnDelete;

    public ForeignKey(String identifier, String fromTable, String toTable, String fromName, String toName,
                      ActionMechanism actionOnUpdate, ActionMechanism actionOnDelete) {
        this.identifier = identifier;
        this.fromTable = fromTable;
        this.toTable = toTable;
        this.fromName = fromName;
        this.toName = toName;
        this.actionOnUpdate = actionOnUpdate;
        this.actionOnDelete = actionOnDelete;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String fromTable() {
        return fromTable();
    }

    public String toTable() {
        return toTable;
    }

    public String fromName() {
        return fromName;
    }

    public String toName() {
        return toName;
    }

    public ActionMechanism getActionOnUpdate() {
         return null;
    }

    public ActionMechanism getActionOnDelete() {
        return null;
    }


}

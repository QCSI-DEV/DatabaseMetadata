package com.qsci.database.metadata.entities.constraints;

public class ForeignKey {
    private String fromTable;
    private String toTable;
    private String fromName;
    private String toName;
    private ActionOnUpdate actionOnUpdate;
    private ActionOnDelete actionOnDelete;

    public ForeignKey(String fromTable, String toTable, String fromName, String toName,
                      ActionOnUpdate onUpdateUpdate, ActionOnDelete OnDelete) {
        this.fromTable = fromTable;
        this.toTable = toTable;
        this.fromName = fromName;
        this.toName = toName;
        this.actionOnUpdate = onUpdateUpdate;
        this.actionOnDelete = OnDelete;
    }

    @Override
    public String toString() {
        return "ForeignKey{" +
                "fromTable='" + fromTable + '\'' +
                ", toTable='" + toTable + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", actionOnUpdate=" + actionOnUpdate +
                ", actionOnDelete=" + actionOnDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForeignKey)) return false;

        ForeignKey that = (ForeignKey) o;

        if (actionOnDelete != null ? !actionOnDelete.equals(that.actionOnDelete) : that.actionOnDelete != null)
            return false;
        if (actionOnUpdate != null ? !actionOnUpdate.equals(that.actionOnUpdate) : that.actionOnUpdate != null)
            return false;
        if (fromName != null ? !fromName.equals(that.fromName) : that.fromName != null) return false;
        if (fromTable != null ? !fromTable.equals(that.fromTable) : that.fromTable != null) return false;
        if (toName != null ? !toName.equals(that.toName) : that.toName != null) return false;
        if (toTable != null ? !toTable.equals(that.toTable) : that.toTable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromTable != null ? fromTable.hashCode() : 0;
        result = 31 * result + (toTable != null ? toTable.hashCode() : 0);
        result = 31 * result + (fromName != null ? fromName.hashCode() : 0);
        result = 31 * result + (toName != null ? toName.hashCode() : 0);
        result = 31 * result + (actionOnUpdate != null ? actionOnUpdate.hashCode() : 0);
        result = 31 * result + (actionOnDelete != null ? actionOnDelete.hashCode() : 0);
        return result;
    }
}

package sg.triquesta.model.dto.request.enums;

public enum SortByApplication {
    CREATION_TIME("creationTime");
    private final String field;

    SortByApplication(String field) {
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}

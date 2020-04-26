package models.entity;

public enum Role {
    OWNER("Владелец"),
    FORESTER("Лесник"),
    ADMIN("Админ");

    private String translation;

    Role(String translation) {
        this.translation = translation;
    }

    public String getTranslationRole(){
        return translation;
    }
}

package br.com.fiap.contatosTelefone.model_01;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role= role;
    }

    public String getRole() {
        return role;
    }
}

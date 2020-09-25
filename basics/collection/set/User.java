class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int hashCode() {
        return this.id * 13;
    }

    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;
        if (this.id == otherUser.getId()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "User : id = " + id + " name = " + name;
    }
}
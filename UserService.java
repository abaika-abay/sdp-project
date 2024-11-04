class UserService {
    private Map<String, User> users;

    public UserService() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
        System.out.println("User added: " + user);
    }

    public void removeUser(String userName) {
        users.remove(userName);
        System.out.println("User " + userName + " removed.");
    }

    public User findUserByName(String userName) {
        return users.get(userName);
    }

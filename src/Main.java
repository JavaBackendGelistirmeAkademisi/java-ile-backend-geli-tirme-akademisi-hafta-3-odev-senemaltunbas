import java.util.*;

public class Main{
    public static void main(String[] args) {
        // Örnek Kullanım
        User user1 = new User("Sema Taş");
        User user2 = new User("Selen Öz");

        user1.follow(user2); // Kullanıcı Takip Etme
        user2.createPost("Yeni çıkan baakım ürünleri"); // Gönderi Oluşturma
        user1.addCommentToPost(user2, 0, "Çok faydalı bir içerik"); // Yorum Ekleme
        user1.addToPostFavorites(user2, 0); // Favorilere Ekleme
        user1.showFeed(); // Ana Sayfa Gösterme
    }

    // Kullanıcı Sınıfı
    static class User {
        private String name;
        private LinkedHashMap<Integer, Post> posts; // Kullanıcının gönderileri
        private HashSet<User> following; // Takip edilen kullanıcılar
        private TreeSet<Post> favorites; // Beğenilen Gönderiler
        private static int postCounter = 0; // Gönderi Sayacı

        public User(String name) {
            this.name = name;
            this.posts = new LinkedHashMap<>();
            this.following = new HashSet<>();
            this.favorites = new TreeSet<>();
        }

        public String getName() {
            return name;
        }

        public void follow(User user) {
            following.add(user);
            System.out.println(name + ", " + user.getName() + " kullanıcısını takip ediyor");
        }

        public void createPost(String content) {
            Post newPost = new Post(postCounter++, this, content);
            System.out.println(name + " yeni bir gönderi yayınladı: " + content);
        }

        public Post getPost(int postId) {
            return posts.get(postId);
        }

        public void addCommentToPost(User user, int postId, String commentContent) {
            Post post = user.getPost(postId);
            if (post != null) {
                post.addComment(new Comment(this, commentContent));
                System.out.println(name + ", " + user.getName() + "'in gönderisine yorum yaptı: " + commentContent);
            }
        }

        public void addToPostFavorites(User user, int postId) {
            Post post = user.getPost(postId);
            if (post != null) {
                favorites.add(post);
                System.out.println(name + ", " + user.getName() + "'in gönderisini beğendi: " + post.getContent());
            }
        }

        public void showFeed() {
            System.out.println("\n" + name + "'in Ana Sayfası");
            for (User user : following) {
                user.showPosts();
            }
        }

        public void showPosts() {
            for (Post post : posts.values()) {
                System.out.println(post.getContent());
            }
        }
    }

    // Gönderi Sınıfı
    static class Post {
        private int id;
        private User user;
        private String content;
        private List<Comment> comments;

        public Post(int id, User user, String content) {
            this.id = id;
            this.user = user;
            this.content = content;
            this.comments = new ArrayList<>();
        }



        public String getContent() {
            return content;
        }

        public void addComment(Comment comment) {
            comments.add(comment);
        }

        public List<Comment> getComments() {
            return comments;
        }
    }

    // Yorum Sınıfı
    static class Comment {
        private User user;
        private String content;

        public Comment(User user, String content) {
            this.user = user;
            this.content = content;
        }

        public User getUser() {
            return user;
        }

        public String getContent() {
            return content;
        }
    }
}

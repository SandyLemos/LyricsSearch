package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.Music;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicListDaoImp implements MusicListDao {

    private static MusicListDaoImp instance;
    public List<Music> MusicList;

    // Caminho
    private static final String MUSIC_FILE = "src/main/java/br/sandy/lyricsSearch/Model/Dao/newMusics_data.txt";

    private MusicListDaoImp() {
        MusicList = new ArrayList<>();
        loadMusics();
    }

    public static synchronized MusicListDaoImp getInstance() {
        if (instance == null) {
            instance = new MusicListDaoImp();
        }
        return instance;
    }

    @Override
    public List<Music> getAllMusics() {
        return MusicList;
    }

    @Override
    public void addMusics(Music musica) {
        MusicList.add(musica);
        saveAllMusicsToFile();
    }

    @Override
    public void updateMusic(int index, Music musica) {
        MusicList.set(index, musica);
        saveAllMusicsToFile();
    }

    @Override
    public Music getMusic(int index) {
        return MusicList.get(index);
    }

    private void saveAllMusicsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MUSIC_FILE))) {
            for (Music musica : MusicList) {
                writer.write("Title:" + musica.getTitle() + "\n");
                writer.write("Artist:" + musica.getArtist() + "\n");
                writer.write("Album:" + musica.getAlbum() + "\n");
                writer.write("Lyric:" + musica.getLyric().replace("\n", "\\n") + "\n");
                writer.write("---\n"); // Separador entre músicas
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMusics() {
        File file = new File(MUSIC_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Music music = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Title:")) {
                    if (music != null) {
                        MusicList.add(music);
                    }
                    String title = line.split(":", 2)[1];
                    music = new Music(title, "", "", "");
                } else if (line.startsWith("Artist:")) {
                    if (music != null) {
                        music.setArtist(line.split(":", 2)[1]);
                    }
                } else if (line.startsWith("Album:")) {
                    if (music != null) {
                        music.setAlbum(line.split(":", 2)[1]);
                    }
                } else if (line.startsWith("Lyric:")) {
                    if (music != null) {
                        music.setLyric(line.split(":", 2)[1].replace("\\n", "\n"));
                    }
                } else if (line.equals("---")) {
                    if (music != null) {
                        MusicList.add(music);
                        music = null;
                    }
                }
            }
            if (music != null) { // Add the last music if the file doesn't end with "---"
                MusicList.add(music);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MusicListDaoImp dao = MusicListDaoImp.getInstance();
        Music testMusic = new Music("Numb", "Linkin Park", "Meteora",
                "I'm tired of being what you want me to be\n" +
                        "Feeling so faithless, lost under the surface\n" +
                        "I don't know what you're expecting of me\n" +
                        "Put under the pressure of walking in your shoes\n" +
                        "Every step that I take is another mistake to you\n" +
                        "(Caught in the undertow, just caught in the undertow)\n" +
                        "I've become so numb, I can't feel you there\n" +
                        "Become so tired, so much more aware\n" +
                        "I'm becoming this, all I want to do\n" +
                        "Is be more like me and be less like you\n" +
                        "Can't you see that you're smothering me?\n" +
                        "Holding too tightly, afraid to lose control\n" +
                        "'Cause everything that you thought I would be\n" +
                        "Has fallen apart right in front of you\n" +
                        "Every step that I take is another mistake to you\n" +
                        "(Caught in the undertow, just caught in the undertow)\n" +
                        "And every second I waste is more than I can take\n" +
                        "I've become so numb, I can't feel you there\n" +
                        "Become so tired, so much more aware\n" +
                        "I'm becoming this, all I want to do\n" +
                        "Is be more like me and be less like you\n" +
                        "And I know\n" +
                        "I may end up failing too\n" +
                        "But I know\n" +
                        "You were just like me, with someone disappointed in you\n" +
                        "I've become so numb, I can't feel you there\n" +
                        "Become so tired, so much more aware\n" +
                        "I'm becoming this, all I want to do\n" +
                        "Is be more like me and be less like you\n" +
                        "I've become so numb, I can't feel you there\n" +
                        "I'm tired of being what you want me to be\n" +
                        "I've become so numb, I can't feel you there\n" +
                        "I'm tired of being what you want me to be");
        dao.addMusics(testMusic);

        // Verificação do resultado
        for (Music music : dao.getAllMusics()) {
            System.out.println("Title: " + music.getTitle());
            System.out.println("Artist: " + music.getArtist());
            System.out.println("Album: " + music.getAlbum());
            System.out.println("Lyric:");
            String[] lines = music.getLyric().split("\\\\n");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("---");
        }
    }

}
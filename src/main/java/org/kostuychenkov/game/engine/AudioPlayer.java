package org.kostuychenkov.game.engine;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Класс аудио-плеера. Содержит статичный метод проигрывания звука по переданному пути файла.
 */
public class AudioPlayer {

    private static final String MUSIC_PATH = "src/main/resources/music";

    /**
     * @param path путь к звуковому файлу.
     * @param playContinuously необходимо ли зацикливать звуковую дорожку(true для музыки, false для звуковых эффектов)
     */
    public static void playMusic(String path, boolean playContinuously) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(MUSIC_PATH + path));

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();

            if(playContinuously)
                clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
package com.korba.gameoff.oblivious.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {

    public enum SelectedProfile {
        CACHE_PROFILE,
        FIRST,
        SECOND,
        THIRD
    }

    private final String MUSIC_VOLUME_KEY = "musicVolume";
    private final String SOUND_VOLUME_KEY = "soundVolume";
    private final String IS_MUTED_KEY = "mute";

    private final float DEFAULT_MUSIC_VOLUME = 1f;
    private final float DEFAULT_SOUND_VOLUME = 1f;
    private final boolean DEFAULT_IS_MUTED = false;

    private final String LAST_USED_PROFILE = "cashedProfile";

    private Preferences prefs;
    private final Preferences defaultPrefs;
    private SelectedProfile profile;
    private float musicVolume;
    private float soundVolume;
    private boolean isMuted;

    public GamePreferences() {
        defaultPrefs = Gdx.app.getPreferences(SelectedProfile.CACHE_PROFILE.toString());
        profile = lastUsedProfile();
    }

    private SelectedProfile lastUsedProfile() {
        if (defaultPrefs.contains(LAST_USED_PROFILE)) {
            profile = SelectedProfile.valueOf(defaultPrefs.getString(LAST_USED_PROFILE));
            prefs = Gdx.app.getPreferences(profile.toString());
            loadProfileSettings();
            if (profile != null) {
                return profile;
            } else {
                return SelectedProfile.CACHE_PROFILE;
            }
        } else {
            return SelectedProfile.CACHE_PROFILE;
        }
    }

    private void loadProfileSettings() {
        musicVolume = prefs.getFloat(MUSIC_VOLUME_KEY);
        soundVolume = prefs.getFloat(SOUND_VOLUME_KEY);
        isMuted = prefs.getBoolean(IS_MUTED_KEY);
    }

    private void loadDefaultSettings() {
        musicVolume = DEFAULT_MUSIC_VOLUME;
        soundVolume = DEFAULT_SOUND_VOLUME;
        isMuted = DEFAULT_IS_MUTED;
    }

    public void setProfile(SelectedProfile profile) {
        this.profile = profile;
        prefs = Gdx.app.getPreferences(profile.toString());
        if (prefs.contains(MUSIC_VOLUME_KEY)) {
            loadProfileSettings();
        } else {
            loadDefaultSettings();
        }
        setProfileSettings();
        setLastUsedProfile();
    }

    private void setLastUsedProfile() {
        defaultPrefs.putString(LAST_USED_PROFILE, profile.toString());
        defaultPrefs.flush();
    }

    private void setProfileSettings() {
        setMusicVolume(musicVolume);
        setSoundVolume(soundVolume);
        setMuted(isMuted);
    }

    public void setMusicVolume(float value) {
        musicVolume = value;
        prefs.putFloat(MUSIC_VOLUME_KEY, musicVolume);
        prefs.flush();
    }

    public void setSoundVolume(float value) {
        soundVolume = value;
        prefs.putFloat(SOUND_VOLUME_KEY, soundVolume);
        prefs.flush();
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
        prefs.putBoolean(IS_MUTED_KEY, isMuted);
        prefs.flush();
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public Preferences getPreferences() {
        return prefs;
    }

    public SelectedProfile getProfile() {
        return profile;
    }

}

package fr.noskillworld.eventapi.api.event;

import org.bukkit.Location;

public class EventConfig {

    private Location spawnLocation = null;
    private String eventName = "Event";
    private int maxPlayerPerTeam = -1;
    private int minPlayerPerTeam = 1;
    private int maxTeams = -1;
    private int minTeams = 1;
    private TeamSelectMode teamSelectMode = TeamSelectMode.RANDOM;

    public EventConfig() {
    }

    public EventConfig(Location spawnLoc, String name, int maxPlayerPerTeam, int minPlayerPerTeam, int maxTeams, int minTeams, TeamSelectMode teamSelectMode) {
        this.spawnLocation = spawnLoc;
        this.eventName = name;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.minPlayerPerTeam = minPlayerPerTeam;
        this.maxTeams = maxTeams;
        this.minTeams = minTeams;
        this.teamSelectMode = teamSelectMode;

        verify(minTeams, maxTeams, minPlayerPerTeam, maxPlayerPerTeam);
    }

    public EventConfig(Location spawnLoc, String name, int maxPlayerPerTeam, int minPlayerPerTeam, int maxTeams, int minTeams) {
        this.spawnLocation = spawnLoc;
        this.eventName = name;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.minPlayerPerTeam = minPlayerPerTeam;
        this.maxTeams = maxTeams;
        this.minTeams = minTeams;

        verify(minTeams, maxTeams, minPlayerPerTeam, maxPlayerPerTeam);
    }

    public EventConfig(Location spawnLoc, String name, int playerPerTeam, int teamCount, TeamSelectMode teamSelectMode) {
        this.spawnLocation = spawnLoc;
        this.eventName = name;
        this.maxPlayerPerTeam = playerPerTeam;
        this.minPlayerPerTeam = playerPerTeam;
        this.maxTeams = teamCount;
        this.minTeams = teamCount;
        this.teamSelectMode = teamSelectMode;
    }

    public EventConfig(Location spawnLoc, int maxPlayerPerTeam, int minPlayerPerTeam, int maxTeams, int minTeams, TeamSelectMode teamSelectMode) {
        this.spawnLocation = spawnLoc;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.minPlayerPerTeam = minPlayerPerTeam;
        this.maxTeams = maxTeams;
        this.minTeams = minTeams;
        this.teamSelectMode = teamSelectMode;

        verify(minTeams, maxTeams, minPlayerPerTeam, maxPlayerPerTeam);
    }

    public EventConfig(Location spawnLoc, int maxPlayerPerTeam, int minPlayerPerTeam, int maxTeams, int minTeams) {
        this.spawnLocation = spawnLoc;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.minPlayerPerTeam = minPlayerPerTeam;
        this.maxTeams = maxTeams;
        this.minTeams = minTeams;

        verify(minTeams, maxTeams, minPlayerPerTeam, maxPlayerPerTeam);
    }

    public EventConfig(Location spawnLoc, int maxPlayerPerTeam, int maxTeams, TeamSelectMode teamSelectMode) {
        this.spawnLocation = spawnLoc;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.maxTeams = maxTeams;
        this.teamSelectMode = teamSelectMode;
    }

    public EventConfig(Location spawnLoc, String name, TeamSelectMode teamSelectMode) {
        this.spawnLocation = spawnLoc;
        this.eventName = name;
        this.teamSelectMode = teamSelectMode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location location) {
        this.spawnLocation = location;
    }

    public int getMaxPlayerPerTeam() {
        return maxPlayerPerTeam;
    }

    public void setMaxPlayerPerTeam(int maxPlayerPerTeam) {
        this.maxPlayerPerTeam = maxPlayerPerTeam;
    }

    public int getMinPlayerPerTeam() {
        return minPlayerPerTeam;
    }

    public void setMinPlayerPerTeam(int minPlayerPerTeam) {
        this.minPlayerPerTeam = minPlayerPerTeam;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public int getMinTeams() {
        return minTeams;
    }

    public void setMinTeams(int minTeams) {
        this.minTeams = minTeams;
    }

    public TeamSelectMode getTeamSelectMode() {
        return teamSelectMode;
    }

    public void setTeamSelectMode(TeamSelectMode teamSelectMode) {
        this.teamSelectMode = teamSelectMode;
    }

    private void verify(int minTeams, int maxTeams, int minPlayerPerTeam, int maxPlayerPerTeam) {
        if (minTeams > maxTeams) {
            this.minTeams = maxTeams;
        }
        if (minPlayerPerTeam > maxPlayerPerTeam) {
            this.minPlayerPerTeam = maxPlayerPerTeam;
        }
    }
}

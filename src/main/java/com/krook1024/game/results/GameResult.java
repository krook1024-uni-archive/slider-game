package com.krook1024.game.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the player.
     */
    @Column(nullable = false)
    private String player;

    /**
     * Indicates whether the player has solved the puzzle.
     */
    private boolean solved;

    /**
     * The number of steps made by the player.
     */
    private int steps;

    /**
     * The duration of the game.
     */
    @Column(nullable = false)
    private Duration duration;

    /**
     * The timestamp when the result was saved.
     */
    @Column(nullable = false)
    private ZonedDateTime created;

    @PrePersist
    protected void onPersist() {
        created = ZonedDateTime.now();
    }
}

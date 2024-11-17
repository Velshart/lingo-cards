package me.mmtr.lingocards.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String definition;

    @Column
    boolean learned = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_set_id", nullable = false)
    private CardSet cardSet;
}

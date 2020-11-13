package it.flowzz.reclaim.profiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReclaimProfile implements Comparable<ReclaimProfile>{

    private List<String> commands;
    private String permission;
    private int weight;

    @Override
    public int compareTo(ReclaimProfile other) {
        return other.weight - weight;
    }
}

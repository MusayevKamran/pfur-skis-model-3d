package ru.pfur.skis.service;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Art on 04.06.16.
 */
@Data
public class ModelDTO {

    Set<NodeDTO> nodes = new HashSet<>();

    Set<BarDTO> bars = new HashSet<>();
}

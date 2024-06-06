package jsu.lmh.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 大学
 */
public class College {
    public Long id;
    public String college_name;
    public String college_address;
    public String college_batch;
}

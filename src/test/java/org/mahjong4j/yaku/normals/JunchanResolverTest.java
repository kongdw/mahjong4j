package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.JUNCHAN;

/**
 * @author yu1ro
 */
public class JunchanResolverTest {
    private JunchanResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(W1));
        list.add(new Sequence(true, T2));
        list.add(new Sequence(false, D2));
        list.add(new Kong(false, D9));
        list.add(new Triplet(true, D1));
        MeldDirectory comp = new MeldDirectory(list, D1);
        resolver = new JunchanResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(JUNCHAN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
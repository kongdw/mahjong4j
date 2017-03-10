package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.RED;
import static org.mahjong4j.tile.Tile.W3;
import static org.mahjong4j.yaku.normals.NormalYaku.RYANPEIKO;

/**
 * @author yu1ro
 */
public class RyanpeikoResolver2Test {
    private RyanpeikoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(RED));
        list.add(new Sequence(false, W3));
        list.add(new Sequence(false, W3));
        list.add(new Sequence(false, W3));
        list.add(new Sequence(false, W3));
        MeldDirectory comp = new MeldDirectory(list, RED);
        resolver = new RyanpeikoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(RYANPEIKO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
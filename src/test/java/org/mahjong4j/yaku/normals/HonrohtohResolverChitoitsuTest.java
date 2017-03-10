package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class HonrohtohResolverChitoitsuTest {
    private HonrohtohResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(W1));
        list.add(new Pair(W9));
        list.add(new Pair(T1));
        list.add(new Pair(T9));
        list.add(new Pair(WEST));
        list.add(new Pair(EAST));
        list.add(new Pair(RED));
        MeldDirectory comp = new MeldDirectory(list, RED);
        resolver = new HonrohtohResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.HONROHTOH, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
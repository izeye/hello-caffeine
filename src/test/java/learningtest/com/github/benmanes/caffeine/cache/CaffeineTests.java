package learningtest.com.github.benmanes.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Caffeine}.
 *
 * @author Johnny Lim
 */
class CaffeineTests {

    @Test
    void statsWithoutRecordStats() {
        Cache<String, String> cache = Caffeine.newBuilder().build();
        assertThat(cache.policy().isRecordingStats()).isFalse();

        cache.put("a", "1");
        assertThat(cache.getIfPresent("a")).isEqualTo("1");
        assertThat(cache.getIfPresent("b")).isNull();

        CacheStats stats = cache.stats();
        assertThat(stats.hitCount()).isEqualTo(0L);
        assertThat(stats.missCount()).isEqualTo(0L);
    }

    @Test
    void statsWithRecordStats() {
        Cache<String, String> cache = Caffeine.newBuilder().recordStats().build();
        assertThat(cache.policy().isRecordingStats()).isTrue();

        cache.put("a", "1");
        assertThat(cache.getIfPresent("a")).isEqualTo("1");
        assertThat(cache.getIfPresent("b")).isNull();

        CacheStats stats = cache.stats();
        assertThat(stats.hitCount()).isEqualTo(1L);
        assertThat(stats.missCount()).isEqualTo(1L);
    }

}

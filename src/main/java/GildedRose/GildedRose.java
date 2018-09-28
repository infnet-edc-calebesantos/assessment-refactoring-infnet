package GildedRose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualityByName(i);
            updateSellIn(i);
            updateQualityToNormalize(i);
        }
    }

	private void updateQualityToNormalize(int i) {
		Item item = items[i];
		if (item.sellIn < 0) {
		    if (!isAgedBrie(i)) {
		        if (!isBackstagePassesToAConcert(i)) {
		            decreaseQualityItem(i);
		        } else {
		            item.quality = item.quality - item.quality;
		        }
		    } else {
		        increaseQualityItem(i);
		    }
		}
	}

	private void updateSellIn(int i) {
		Item item = items[i];
		if (!isSulfurasHandOfRagnaros(i)) {
			item.sellIn = item.sellIn - 1;
		}
	}

	private void updateQualityByName(int i) {
		if (!isAgedBrie(i) && !isBackstagePassesToAConcert(i)) {
		    decreaseQualityItem(i);
		} else {
		    if (isInQualityLimitToIncrease(i)) {
		        Item item = items[i];
				item.quality = item.quality + 1;

		        if (isBackstagePassesToAConcert(i)) {
		            if (item.sellIn < 11) {
		                increaseQualityItem(i);
		            }

		            if (item.sellIn < 6) {
		                increaseQualityItem(i);
		            }
		        }
		    }
		}
	}

	private boolean isSulfurasHandOfRagnaros(int i) {
		return isNameEquals(i, "Sulfuras, Hand of Ragnaros");
	}

	private boolean isBackstagePassesToAConcert(int i) {
		return isNameEquals(i, "Backstage passes to a TAFKAL80ETC concert");
	}

	private boolean isAgedBrie(int i) {
		return isNameEquals(i, "Aged Brie");
	}

	private boolean isInQualityLimitToIncrease(int i) {
		return items[i].quality < 50;
	}

	private boolean isNameEquals(int i, String valueToCompare) {
		return items[i].name.equals(valueToCompare);
	}

	private void increaseQualityItem(int i) {
		if (isInQualityLimitToIncrease(i)) {
		    items[i].quality = items[i].quality + 1;
		}
	}

	private void decreaseQualityItem(int i) {
		if (items[i].quality > 0) {
		    if (!isSulfurasHandOfRagnaros(i)) {
		        items[i].quality = items[i].quality - 1;
		    }
		}
	}
}
package basics.fp.designWithFp;

import basics.fp.JavaBean.Asset;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Design {
    public static void main(String[] args) {
        final List<Asset> assets = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000)
        );

//        System.out.println("Total value is: " + totalAssetValues(assets));

        System.out.println("Total value is: "
                + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
    }

    // strategy pattern で実装するべきコード
//    private static int totalAssetValues(final List<Asset> assets) {
//        return assets.stream()
//                .mapToInt(Asset::getValue)
//                .sum();
//    }

    private static int totalAssetValues(final List<Asset> assets,
                                        final Predicate<Asset> assetSelector) {
        return assets.stream()
                .filter(assetSelector)
                .mapToInt(Asset::getValue)
                .sum();
    }
}


package com.priyanshu.floralens.data

data class DiseaseInfo(
    val displayName: String,
    val cause: String,
    val treatment: String
)

object DiseaseDatabase {
    private val database = mapOf(
        "Apple___Apple_scab" to DiseaseInfo(
            displayName = "Scab",
            cause = "• Caused by the ascomycete fungus Venturia inaequalis.\n• The pathogen overwinters in infected leaf litter on the orchard floor.\n• In early spring, moisture triggers the release of ascospores into the air, which land on and infect fresh leaf tissue.\n• It thrives in prolonged wet, cool spring weather (55 to 70 degrees Fahrenheit) and spreads rapidly through rain splashes and wind-borne spores.",
            treatment = "• Rake, remove, and destroy all fallen leaves and debris in autumn to destroy overwintering fungi.\n• Prune trees to open up the canopy, improving sun exposure and air circulation to dry leaves quickly.\n• Apply preventative copper-based or sulfur-based organic fungicides during the green tip and pink stages of bud development.\n• Spray neem oil for mild infections."
        ),
        "Apple___Black_rot" to DiseaseInfo(
            displayName = "Black Rot",
            cause = "• Initiated by the fungal pathogen Botryosphaeria obtusa.\n• The fungus infects leaves (causing frog-eye leaf spot), bark (causing cankers), and fruit.\n• It overwinters in dead wood, mummified fruits left hanging on the tree, and bark cankers.\n• Spores are released during warm, wet spring rains and infect leaves or fruits through wounds, insect bites, or natural openings.",
            treatment = "• Prune out all infected twigs, branches, and active cankers during the dormant season, cutting at least 6 inches into healthy wood.\n• Collect and burn or bury all fallen fruit and pruned wood.\n• Apply protective organic fungicides, such as sulfur or liquid copper, starting at bud break and continuing throughout the wet spring months."
        ),
        "Apple___Cedar_apple_rust" to DiseaseInfo(
            displayName = "Cedar Rust",
            cause = "• A complex fungal disease caused by Gymnosporangium juniperi-virginianae.\n• It requires two hosts to complete its life cycle: juniper/red cedar trees and apple trees.\n• In wet spring conditions, gelatinous orange galls on junipers release wind-borne basidiospores that travel up to a mile to infect apple foliage and developing fruit.",
            treatment = "• Eradicate or remove ornamental eastern red cedars within a 1-mile radius if feasible.\n• Prune out juniper galls before they produce orange spore horns in spring.\n• Apply protective organic fungicides like sulfur or copper soap to the apple foliage when apple buds show pink, repeating every 7 to 10 days until flower petals drop."
        ),
        "Apple___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• The plant is showing optimal physiological health with no signs of active disease or pathogen invasion.\n• This indicates ideal growing conditions, including adequate sunlight, clean air circulation, balanced soil nutrients, and proper watering schedules.",
            treatment = "• Continue current horticultural practices.\n• Apply compost annually to enrich soil structure, maintain a 2-inch organic mulch layer to conserve soil moisture, and prune selectively during winter dormancy to promote structural integrity and leaf aeration."
        ),
        "Blueberry___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Excellent physical condition.\n• The plant demonstrates high vigor, optimal chlorophyll production, and robust cell wall integrity.\n• Soil acidity and moisture retention are ideal, supporting standard nutrient absorption.",
            treatment = "• Monitor soil pH to keep it within the preferred acidic range (4.5 to 5.0).\n• Mulch with pine needles, peat moss, or wood chips to maintain low soil pH and retain moisture.\n• Practice deep, infrequent watering at the base."
        ),
        "Cherry_(including_sour)__Powdery_mildew" to DiseaseInfo(
            displayName = "Powdery Mildew",
            cause = "• Caused by the obligate biotrophic fungus Podosphaera clandestina.\n• The fungus overwinters as cleistothecia in bark crevices and leaf litter.\n• In spring, wind and rain carry spores to young leaves.\n• Unlike most fungi, it thrives in warm, dry weather with high relative humidity, causing white mycelial patches.",
            treatment = "• Prune lower branches and dense center limbs to lower humidity inside the canopy.\n• Apply organic horticultural oils, potassium bicarbonate, or sulfur sprays at the first sign of white powdery spots.\n• Ensure overhead irrigation is avoided, watering instead at ground level."
        ),
        "Cherry(including_sour)__healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• The leaf exhibits rich green coloring and intact stomata.\n• Photosynthetic pathways are functioning correctly without pathogenic interference.",
            treatment = "• Maintain steady irrigation during the fruit-development phase.\n• Prune late in winter to remove dead or overlapping branches, which prevents future fungal colonizations.\n• Inspect periodically for insect vectors."
        ),
        "Corn(maize)__Cercospora_leaf_spot Gray_leaf_spot" to DiseaseInfo(
            displayName = "Gray Leaf Spot",
            cause = "• Caused by the fungal pathogen Cercospora zeae-maydis.\n• It overwinters in corn crop residue left on the soil surface.\n• Spores are produced in spring and carried by wind and splashing water.\n• High humidity (over 90%) and warm temperatures (75 to 90 degrees Fahrenheit) trigger infection, causing rectangular gray lesions.",
            treatment = "• Implement crop rotation (avoiding corn for at least one year) and deep tillage to bury crop debris and promote decomposition.\n• Plant resistant corn hybrids.\n• Apply organic bio-fungicides containing Bacillus subtilis if high humidity persists during silking."
        ),
        "Corn(maize)_Common_rust" to DiseaseInfo(
            displayName = "Common Rust",
            cause = "• Caused by the rust fungus Puccinia sorghi.\n• The fungus cannot survive cold winters in northern zones, and instead wind-borne urediniospores are carried annually from southern, warmer climates.\n• Cool temperatures (60 to 75 degrees Fahrenheit) and high relative humidity with free dew on leaves favor spore germination.",
            treatment = "• Plant rust-resistant varieties.\n• Improve field drainage and spacing to accelerate leaf drying.\n• For small-scale crops, spray sulfur or copper soap solutions early in the season if rust pustules appear on lower leaves."
        ),
        "Corn(maize)__Northern_Leaf_Blight" to DiseaseInfo(
            displayName = "Northern Leaf Blight",
            cause = "• Caused by the ascomycete fungus Exserohilum turcicum.\n• The fungus overwinters as chlamydospores in infected corn residue.\n• In late spring, wind and splashing rain carry spores to lower leaves.\n• The disease progresses rapidly in moderate temperatures (64 to 80 degrees Fahrenheit) and prolonged dew.",
            treatment = "• Practice crop rotation with non-cereal crops and till soil to bury crop residues.\n• Select resistant hybrids.\n• Space plants to ensure good airflow.\n• In severe cases, apply preventive organic bio-fungicides at the first sign of cigar-shaped tan lesions."
        ),
        "Corn(maize)healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• The leaf displays deep green color and crisp texture with no fungal lesions or rust pustules, indicating optimal nitrogen uptake, balanced soil moisture, and strong root systems.",
            treatment = "• Side-dress with high-quality compost or organic nitrogen-rich fertilizer during rapid growth stages.\n• Ensure consistent watering, especially during the critical pollination and silking periods."
        ),
        "Grape___Black_rot" to DiseaseInfo(
            displayName = "Black Rot",
            cause = "• Caused by Guignardia bidwellii, a highly destructive fungus.\n• It overwinters in infected canes and mummified grape berries on the vine or ground.\n• Rain triggers spore release, infecting all young, green tissues, especially when leaves remain wet for over 7 hours.",
            treatment = "• Clean sanitation is crucial; remove all mummified grapes and prune out infected canes during dormancy.\n• Apply copper or sulfur fungicides early in the spring, from bud break until bloom, to protect developing berries from infection."
        ),
        "Grape___Esca(Black_Measles)" to DiseaseInfo(
            displayName = "Esca (Black Measles)",
            cause = "• A complex wood-decay disease caused by a consortium of fungi, including Phaeomoniella chlamydospora and Phaeoacremonium aleophilum.\n• The fungi enter through pruning wounds and slowly destroy the vascular system, producing wood rot and leaf striping ('tiger-stripe' pattern).",
            treatment = "• Disinfect pruning tools with alcohol or sanitizing solutions between vines to prevent spreading.\n• Apply organic pruning wound sealants immediately after cutting.\n• Remove and burn severely infected vines to protect the vineyard."
        ),
        "Grape___Leaf_blight(Isariopsis_Leaf_Spot)" to DiseaseInfo(
            displayName = "Leaf Blight",
            cause = "• Caused by the fungus Pseudocercospora vitis.\n• It overwinters on fallen leaves and debris.\n• Warm, moist, and humid environments encourage spore germination, leading to dark brown lesions on older leaves, which can lead to premature leaf drop.",
            treatment = "• Rake and compost or destroy fallen grape leaves in autumn to eliminate overwintering spores.\n• Train grapevines on high trellises and prune dense shoots to improve sun penetration and air movement.\n• Apply organic fungicides if wet weather persists."
        ),
        "Grape___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Vine foliage is vibrant green, showing strong canopy growth and high fruit-bearing potential.\n• Photosynthesis is highly active, supported by healthy root development.",
            treatment = "• Perform structural pruning during late winter to manage vine size.\n• Maintain balanced drip irrigation to avoid overwatering, and test soil to ensure optimal potassium and micronutrient levels."
        ),
        "Orange___Haunglongbing(Citrus_greening)" to DiseaseInfo(
            displayName = "Citrus Greening",
            cause = "• Caused by the phloem-limited bacterium Candidatus Liberibacter asiaticus.\n• The bacterium is transmitted by the Asian citrus psyllid (Diaphorina citri) insect vector.\n• It disrupts the tree's nutrient transport system, leading to mottled yellow leaves, stunted growth, and bitter, lopsided green fruit.",
            treatment = "• Since no organic or chemical cure exists, control focus is entirely on vector management and prevention.\n• Control psyllids using organic horticultural oils, insecticidal soaps, or releasing natural predators like Tamarixa radiata.\n• Promptly remove and destroy infected trees to prevent infecting neighboring orchards."
        ),
        "Peach___Bacterial_spot" to DiseaseInfo(
            displayName = "Bacterial Spot",
            cause = "• Caused by the bacterium Xanthomonas campestris pv.\n• pruni.\n• It overwinters in twig cankers and buds.\n• Warm, wet spring conditions with heavy rains and high winds spread the bacteria through stomata or mechanical leaf wounds.",
            treatment = "• Plant cultivars resistant to bacterial spot.\n• Apply protective copper sprays or organic bactericides early in the spring during bud swell and leaf fall.\n• Avoid excessive nitrogen fertilization, which generates highly susceptible succulent growth."
        ),
        "Peach___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Vigorous foliage, smooth leaf margins, and robust growth, free of bacterial spots or fungal cankers.",
            treatment = "• Prune trees annually to keep an open-center structure for ventilation.\n• Spray lime-sulfur during winter dormancy as a preventative sanitation practice."
        ),
        "Pepper,_bell___Bacterial_spot" to DiseaseInfo(
            displayName = "Bacterial Spot",
            cause = "• Caused by several species of the bacterium Xanthomonas (e.g., X.\n• euvesicatoria).\n• The pathogen is seed-borne or survives in infested crop debris.\n• It spreads via splashing rain, overhead irrigation, and contaminated garden tools in warm, humid weather.",
            treatment = "• Plant certified disease-free seeds and seedlings.\n• Avoid overhead watering; use drip lines to keep foliage dry.\n• Apply organic copper-based bactericides at the first sign of leaf spots.\n• Disinfect tools before working with healthy plants."
        ),
        "Pepper,_bell___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Foliage is deep green, upright, and showing robust growth, with a highly active vascular system and healthy root structure.",
            treatment = "• Mulch plants to retain soil moisture and regulate soil temperature.\n• Provide consistent watering and support with tomato cages to protect heavy fruit loads."
        ),
        "Potato___Early_blight" to DiseaseInfo(
            displayName = "Early Blight",
            cause = "• Caused by the fungus Alternaria solani.\n• It overwinters in infested soil and crop debris.\n• Spores land on lower, older leaves and germinate during alternating wet and dry conditions.\n• Warm temperatures (75 to 85 degrees Fahrenheit) accelerate the disease, producing target-like concentric rings on leaves.",
            treatment = "• Practice a 3-year crop rotation avoiding nightshade family plants.\n• Maintain plant vigor with balanced compost.\n• Apply protective organic copper fungicides or bio-fungicides like Bacillus amyloliquefaciens early in the season."
        ),
        "Potato___Late_blight" to DiseaseInfo(
            displayName = "Late Blight",
            cause = "• Caused by the highly destructive oomycete Phytophthora infestans.\n• It overwinters in infected potato seed tubers or cull piles.\n• Cool, wet, foggy conditions (60 to 70 degrees Fahrenheit) trigger rapid spore production and spread by wind.",
            treatment = "• Plant only certified disease-free seed potatoes.\n• Destroy volunteer potatoes and cull piles.\n• Apply organic copper-based fungicides preventatively when cool, wet weather is forecast.\n• Harvest tubers only after foliage has completely dried to prevent spore transmission."
        ),
        "Potato___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Foliage is full, rich green, and showing robust photosynthesis.\n• Stems are strong and root tubers are developing healthily under well-drained soil conditions.",
            treatment = "• Hill plants regularly by piling soil around the stems to cover growing tubers.\n• Ensure consistent watering but avoid soggy soils to prevent root rot."
        ),
        "Raspberry___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Strong primocanes and floricanes with deep green leaves and healthy bud set.\n• Excellent nutrient assimilation and cell wall structure.",
            treatment = "• Prune out spent floricanes at ground level after harvesting to allow primocanes to grow.\n• Provide trellising to keep leaves off the ground and improve air flow."
        ),
        "Soybean___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Robust crop stand with high nitrogen-fixing nodule activity on roots.\n• Leaves are clear, showing high photosynthetic efficiency and zero fungal or insect lesions.",
            treatment = "• Ensure soil is inoculated with beneficial Rhizobium bacteria during planting.\n• Maintain adequate phosphorus and potassium levels, and keep the field clear of weed competition."
        ),
        "Squash___Powdery_mildew" to DiseaseInfo(
            displayName = "Powdery Mildew",
            cause = "• Caused by Podosphaera xanthii or Erysiphe cichoracearum fungi.\n• The fungi overwinter on weed hosts and crop residue.\n• High relative humidity and warm temperatures (70 to 80 degrees Fahrenheit) favor spore development.\n• Unlike other fungi, leaf wetness actually inhibits spore germination.",
            treatment = "• Grow squash in full sun and space vines widely to improve ventilation.\n• Spray leaves preventatively with a milk-water mixture (1:9 ratio), neem oil, or potassium bicarbonate solutions.\n• Rake and compost all vines at season end."
        ),
        "Strawberry___Leaf_scorch" to DiseaseInfo(
            displayName = "Leaf Scorch",
            cause = "• Caused by the fungal pathogen Diplocarpon earlianum.\n• The fungus overwinters on infected leaves.\n• In spring, ascocarps produce spores that are splashed by rain or irrigation onto new leaves, germinating rapidly when moisture remains on the foliage for over 12 hours.",
            treatment = "• Rake and destroy old leaf debris after harvesting.\n• Avoid overhead sprinkler systems, utilizing drip irrigation instead.\n• Space plants properly to ensure foliage dries rapidly after rain.\n• Apply organic copper or sulfur-based fungicides in early spring."
        ),
        "Strawberry___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Healthy green runners, leaves, and crowns.\n• Excellent moisture regulation, clean white blossoms, and uniform berry development.",
            treatment = "• Maintain a clean straw mulch layer under the foliage to protect fruit from direct soil contact.\n• Water regularly, especially during fruit production."
        ),
        "Tomato___Bacterial_spot" to DiseaseInfo(
            displayName = "Bacterial Spot",
            cause = "• Caused by Xanthomonas perforans or Xanthomonas vesicatoria.\n• The bacteria survive on contaminated seeds and in infected crop residue.\n• It spreads rapidly via wind-driven rain, overhead watering, and direct contact in hot (75 to 90 degrees Fahrenheit), wet conditions.",
            treatment = "• Buy certified disease-free seeds or treat seeds with hot water before planting.\n• Use drip irrigation.\n• Apply copper fungicides combined with sulfur or organic bio-bactericides early in the disease cycle.\n• Prune lower branches to keep them off the soil."
        ),
        "Tomato___Early_blight" to DiseaseInfo(
            displayName = "Early Blight",
            cause = "• Caused by the fungus Alternaria solani.\n• It overwinters in soil and crop debris.\n• Spores splash onto lower leaves, producing dark spots with concentric 'target' rings.\n• High humidity and moderate temperatures (75 to 85 degrees Fahrenheit) encourage growth.",
            treatment = "• Prune off the bottom 12 inches of leaves to prevent soil-splash contact.\n• Apply a thick layer of organic mulch.\n• Use crop rotation (3 years).\n• Spray organic copper or bio-fungicides containing Bacillus subtilis regularly during warm, humid weeks."
        ),
        "Tomato___Late_blight" to DiseaseInfo(
            displayName = "Late Blight",
            cause = "• Caused by the water mold Phytophthora infestans.\n• Spores are wind-borne and travel miles in cool, wet, humid weather.\n• It can destroy entire crops within days, turning leaves dark brown and rotting fruits.",
            treatment = "• Remove and bag infected plants immediately to prevent spores from blowing to other gardens.\n• Do not compost infected debris.\n• Apply preventive copper soap or organic bio-fungicides weekly during periods of cool, wet weather."
        ),
        "Tomato___Leaf_Mold" to DiseaseInfo(
            displayName = "Leaf Mold",
            cause = "• Caused by the fungus Passalora fulva.\n• It survives on crop residues and greenhouse structures.\n• It requires high relative humidity (above 85%) and moderate temperatures (65 to 80 degrees Fahrenheit) to thrive, causing olive-green velvety patches on leaf undersides.",
            treatment = "• Crucial to reduce humidity; maximize ventilation in greenhouses using fans.\n• Space plants widely and prune suckers to increase airflow.\n• Spray potassium bicarbonate or neem oil on leaf undersides if symptoms emerge."
        ),
        "Tomato___Septoria_leaf_spot" to DiseaseInfo(
            displayName = "Septoria Leaf Spot",
            cause = "• Caused by the fungus Septoria lycopersici.\n• It overwinters on solanaceous weeds and crop debris.\n• Splashing water from rain or hose sprays transfers spores to lower leaves, resulting in numerous small grey spots with dark borders.",
            treatment = "• Avoid overhead watering; target the root zone with drip lines.\n• Apply organic mulch under plants.\n• Prune infected lower leaves as soon as spots appear to halt upward progression.\n• Clean all cages and stakes with disinfectant at season end."
        ),
        "Tomato___Spider_mites Two-spotted_spider_mite" to DiseaseInfo(
            displayName = "Spider Mites",
            cause = "• Caused by Tetranychus urticae (two-spotted spider mite).\n• These tiny, arachnid pests thrive in hot, dry, dusty conditions.\n• They feed by puncturing leaf cells to suck sap, causing yellow stippling and fine webbing on leaf undersides.",
            treatment = "• Spray the undersides of leaves with a strong stream of water daily to knock off mites and disrupt webbing.\n• Apply organic insecticidal soaps, horticultural oils, or neem oil weekly.\n• Introduce beneficial predatory mites like Phytoseiulus persimilis."
        ),
        "Tomato___Target_Spot" to DiseaseInfo(
            displayName = "Target Spot",
            cause = "• Caused by the fungus Corynespora cassiicola.\n• It survives on crop debris and wild solanaceous hosts.\n• High humidity and warm temperatures (68 to 82 degrees Fahrenheit) favor spore germination.\n• It produces circular brown spots with faint target-like rings.",
            treatment = "• Prune to maintain a single or double stem to increase sunlight penetration and air movement.\n• Apply mulch to separate foliage from soil.\n• Spray copper fungicides or organic bio-fungicides to protect new growth."
        ),
        "Tomato___Tomato_Yellow_Leaf_Curl_Virus" to DiseaseInfo(
            displayName = "Yellow Leaf Curl Virus",
            cause = "• Transmitted exclusively by the silverleaf whitefly (Bemisia tabaci) vector.\n• The virus halts chlorophyll development, causing leaflets to roll upwards, turn pale yellow, and stunt the plant's overall growth.",
            treatment = "• Cover young plants with fine insect netting to exclude whiteflies.\n• Apply organic neem oil or insecticidal soap to control whitefly populations on leaf undersides.\n• Immediately pull up and discard infected plants to stop the virus from spreading."
        ),
        "Tomato___Tomato_mosaic_virus" to DiseaseInfo(
            displayName = "Mosaic Virus",
            cause = "• Caused by the highly stable and contagious Tobacco/Tomato Mosaic Virus (TMV/ToMV).\n• It spreads mechanically via hands, tools, clothing, or direct plant contact, and can survive for decades in dry plant tissue or soil.",
            treatment = "• There is no cure; pull up and burn or throw away infected plants.\n• Wash hands with soap and disinfect pruning tools with a 10% bleach solution or household disinfectant before handling healthy plants.\n• Do not use tobacco products near plants."
        ),
        "Tomato___healthy" to DiseaseInfo(
            displayName = "Healthy Plant",
            cause = "• Excellent plant vigor, fully operational vascular network, and normal chloroplast distribution.\n• Optimal soil nutrition (balanced nitrogen, phosphorus, and potassium) and watering support strong immunity.",
            treatment = "• Maintain consistent moisture levels using drip irrigation and mulch.\n• Prune side suckers to concentrate energy on main stems and fruit development.\n• Stake or cage vines to keep leaves and fruit clean and off the ground."
        )
    )

    fun getInfo(label: String): DiseaseInfo {
        return database[label] ?: DiseaseInfo(
            displayName = label.replace("_", " ").trim(),
            cause = "• Unknown cause.\n• Ensure plant is receiving adequate light, water, and nutrients.",
            treatment = "• Monitor the plant closely for worsening symptoms.\n• Consult a local nursery if needed."
        )
    }
}

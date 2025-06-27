コンパイル
javac GeneticAlgo.java
実行
java GeneticAlgo

how to use 
fileconfig.xml
でアプリの動作を設定します。
    <readfilePath>ファイル名までを含めた、元ファイルまでのパス</readfilePath>\n
    <writefilePath>処理が終わったときに作られるpngファイルまでのパス</writefilePath>
    <individual_max>世代一つの個体（画像）数</individual_max>
    <generation_max>最大世代数</generation_max>
    <survived_individual>交配させる個体（画像）の数</survived_individual>
    <file_out>処理が終わったときにファイルを出力するかしないか（true or false）</file_out>
    <mutation>突然変異の確率</mutation>
    <thread>スレッドの数</thread>

    スレッドの数はCPUのスレッド数を参考に調節してください。
    スレッド数 ＝　√スレッド数が整数となるように設定してください。
    現在使用できる画像の最大サイズは300×300pxで、正方形の画像を指定してください。
    

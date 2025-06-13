使い方

Javaを使うよ。
環境構築、実行方法は割愛するよ。

fileconfig.xmlからアプリの設定を行う。
数学とかで使う、大なり小なりの記号で囲っているのがタグ名です。
<タグ名></タグ名>
↑　こんな感じ。
readfilePath： 元となる画像までのファイルパス
writefilePath：　出来上がった画像の保存先
individual_max：　一世代ごとに生成される画像の数
generation_max：　最大世代数
survived_individual：生き残れる画像の数
file_out：処理後にファイルを出力するか否か、true:する、false:しない
mutation：突然変異の確率
thread：処理する際のスレッド数


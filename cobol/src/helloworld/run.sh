for f in *.cbl
do
    echo "---------- $f ---------------"
    cobc -x "$f"
    ./${f%.*}
done

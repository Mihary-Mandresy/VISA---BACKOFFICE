import re

line = []

# ! Lecture de create

pathSprint = "./migration/Sprint1"

with open(f"{pathSprint}/create.sql") as f:
    
    lines = f.readlines()
        
    p = re.compile("\\s*CREATE\\s+TABLE\\s+([a-zA-Z]+).*", re.IGNORECASE)
    for l in lines:
        v = p.findall(l)
        if v :
            line += v
            
line.reverse()

# Generation des sql

line = [l.replace("\n", "") for l in line]

strdropTable = "DROP TABLE IF EXISTS "

joinLine = ",\n".join(line)

strdropTable += joinLine + " CASCADE;\n"

# ! Drop

fldrop = open(f"{pathSprint}/drop.sql", "w")
fldrop.write(strdropTable + "\n")
fldrop.write("DROP SEQUENCE IF EXISTS " + ",\n".join([f"seq_{l}" for l in line]) + ";")
fldrop.close()

# ! Truncate

fltruncate = open(f"{pathSprint}/truncate.sql", "w")
fltruncate.write("TRUNCATE TABLE " + joinLine + ";\n\n")
fltruncate.write("\n".join([f"ALTER SEQUENCE seq_{l} RESTART WITH 1;" for l in line]))
fltruncate.close()

# ! Create Sequence

flseqcreate = open(f"{pathSprint}/seq.sql", "w")
flseqcreate.writelines("\n".join([f"CREATE SEQUENCE seq_{l} START WITH 1;" for l in line]))
flseqcreate.close()


object M4c {



type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions


def is_legal(dim: Int, path: Path, x: Pos) : Boolean = {
  x._1 < dim && x._1 >= 0 && x._2 < dim && x._2 >= 0 && !path.contains(x)
}



def legal_moves(dim: Int, path: Path, x: Pos) : List[Pos] = {
  val vecs = List((1,2),(2,1),(2,-1),(1,-2),(-1,-2),(-2,-1),(-2,1),(-1,2))
  vecs.map(jump => (jump._1 + x._1, jump._2 + x._2)).filter(new_pos => is_legal(dim,path,new_pos))
  
}
def first(xs: List[Pos], f: Pos => Option[Path]) : Option[Path] = {
  xs match{
    case Nil => None
    case head::tail => {
      val out = f(head)
      if (out.isDefined) out
      else first(tail,f)
    }
  }
}


def ordered_moves(dim: Int, path: Path, x: Pos) : List[Pos] = {
    legal_moves(dim,path,x).sortBy(p => legal_moves(dim,path,p).length)
}


def first_tour_heuristics(dim: Int, path: Path) : Option[Path] = {
    if (path.length == dim*dim) Some(path)
    else{
        first(ordered_moves(dim, path, path.head),(p: Pos ) => first_tour_heuristics(dim, p :: path))
    }
}

def tour_recurse(dim: Int, path_list: List[Path]) : Option[Path] = {
    path_list match {
        case Nil => None
        case path::rest_of_path => {
            if (path.length == dim * dim) Some(path)
            else{
                val new_paths = ordered_moves(dim, path, path.head).map(new_pos => new_pos :: path)
                tour_recurse(dim, new_paths:::rest_of_path)

            }
        } 
    }
}

def tour(dim: Int, path: Path) : Option[Path] = {
    tour_recurse(dim,List(path))
    
}


def time_needed[T](code: => T) : T = {
  val start = System.nanoTime()
  val result = code
  val end = System.nanoTime()
  println(f"Time needed: ${(end - start) / 1.0e9}%3.3f secs.")
  result
}



}

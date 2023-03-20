schema([a,b,c,d,e]).
fds([ [[a],[b]], [[a,c],[d]], [[e],[a,b]], 
      [[a,d],[e]], [[a,b,c],[d]] ]).
displayFDs([]) :- nl.
displayFDs([[X,Y]|Rest]) :-
  write(X),write('-->'), write(Y),nl,displayFDs(Rest).
go :- schema(R), fds(F), fplus(R,F,Fplus), displayFDs(Fplus).
